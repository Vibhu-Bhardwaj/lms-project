pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git branch: 'main', url: 'https://github.com/Vibhu-Bhardwaj/lms-project.git'
            }
        }
        stage('Install Docker Compose') {
            steps {
                sh '''
                curl -L "https://github.com/docker/compose/releases/download/2.20.2/docker-compose-$(uname -s)-$(uname -m)" -o ./docker-compose
                chmod +x ./docker-compose
                export PATH=$PATH:$PWD
                '''
            }
        }
        stage('Build') {
            steps {
                // Build the Spring Boot app using Docker Compose
                sh 'docker-compose down'
                sh 'docker-compose up --build -d'
            }
        }
        stage('Test') {
            steps {
                // Add your testing commands here
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Application deployed successfully!'
            }
        }
    }

    post {
        always {
            sh 'docker-compose down'
        }
    }
}
