pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
                sh './mvnw clean install'  // Build the application using Maven Wrapper
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'  // Run the unit tests using Maven Wrapper
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()  // Clean the workspace after each run
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
    }
}
