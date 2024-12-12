pipeline {
    agent any
    
    environment {
        IMAGE_NAME = 'michaelo001/test-java-app'
        APP_NAME='java-app'
        DOCKER_CREDENTIALS_ID
    }

    stages {
        stage('checkout') {
            steps {
                checkout scm
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
                    docker.withDockerRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        docker.image("${IMAGE_NAME}:${BUILD_NUMBER}").push()

                        docker.image("${IMAGE_NAME}:${BUILD_NUMBER}").push('latest')
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    //If docker stop fails (maybe for e.g., container doesn't exist), the script continues due to this part "|| true"
                    sh '''
                        docker stop APP_NAME|| true 
                        docker rm APP_NAME || true
                    '''

                    sh '''
                        docker run -d --name ${APP_NAME} -p 8080:8080 ${IMAGE_NAME}:${BUILD_NUMBER}
                    '''
                }
            }
        }

        stage('Cleanup') {
            steps {
                // Remove local images to save disk space
                sh '''
                    docker image prune -f
                '''
            }
        }
    }
}