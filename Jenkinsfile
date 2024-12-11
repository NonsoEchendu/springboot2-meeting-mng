pipeline {
    agent any
    
    environment {
        IMAGE_NAME = 'michaelo001/test-java-app'
        APP_NAME='java-app'

    }

    stages {
        stage('checkout') {
            checkout scm
        }

        stage('Build docker image') {
            script {
                docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
            }
        }

        stage('Push to docker repo') {
            script {
                docker.withRegistry()
            }
        }
    }
}