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
                curl -L "https://github.com/docker/compose/releases/download/2.20.2/docker-compose-$(uname -s)-$(uname -m)" -o ./docker-compose
                if [ $? -ne 0 ]; then
                    echo "Failed to download Docker Compose. Exiting."
                    exit 1
                fi
                chmod +x ./docker-compose
                '''
            }
        }

        stage('Verify Docker Compose') {
            steps {
                sh '''
                echo "Verifying Docker Compose installation..."
                ./docker-compose --version
                if [ $? -ne 0 ]; then
                    echo "Docker Compose verification failed. Exiting."
                    exit 1
                fi
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                echo "Shutting down any existing containers..."
                ./docker-compose down || true
                
                echo "Building and starting containers..."
                ./docker-compose up --build -d
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests... (Add actual test commands here if needed)'
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
            sh '''
            echo "Cleaning up containers..."
            ./docker-compose down
            '''
        }
    }
}
