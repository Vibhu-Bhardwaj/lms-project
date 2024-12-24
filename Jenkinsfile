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
                echo "Downloading Docker Compose..."
                curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                chmod +x /usr/local/bin/docker-compose
                ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
                '''
            }
        }

        stage('Verify Docker Compose') {
            steps {
                sh '''
                echo "Verifying Docker Compose installation..."
                docker-compose --version
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                echo "Stopping existing containers..."
                docker-compose down || true

                echo "Building and starting containers..."
                docker-compose up --build -d
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests... (Add your test scripts here)'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
            }
        }
    }

    post {
        always {
            sh '''
            echo "Cleaning up containers..."
            docker-compose down || true
            '''
        }
    }
}
