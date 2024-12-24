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
                '''
            }
        }
        stage('Build') {
            steps {
                // Use the locally downloaded docker-compose
                sh './docker-compose down || true'
                sh './docker-compose up --build -d'
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
            // Use the locally downloaded docker-compose
            sh './docker-compose down'
        }
    }
}
