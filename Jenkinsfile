pipeline {
    agent any
    
    environment {
        IMAGE_NAME = 'michaelo001/test-java-app'
        APP_NAME = 'java-app'
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
    }

    stages {

        stage('checkout') {
            steps {
                checkout scm
            }
        }

        stage('Check for Docker') {
            steps {
                script {
                    sh '''
                        echo "checking if docker exists"
                        docker ps
                    '''
                }
            }
        }

        stage('Build docker image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                }
            }
        }

        stage('Push to docker repo') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh """
                            echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin
                            docker push ${IMAGE_NAME}:${BUILD_NUMBER}
                            docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest
                            docker push ${IMAGE_NAME}:latest
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    //If docker stop fails (maybe for e.g., container doesn't exist), the script continues due to this part "|| true"
                    sh """
                        docker stop ${APP_NAME} || true 
                        docker rm ${APP_NAME} || true
                        docker run -d --name ${APP_NAME} -p 8080:8080 ${IMAGE_NAME}:${BUILD_NUMBER}
                    """
                }
            }
        }

        stage('Cleanup') {
            steps {
                // Remove local images to save disk space
                sh 'docker image prune -f'
            }
        }
    }
}