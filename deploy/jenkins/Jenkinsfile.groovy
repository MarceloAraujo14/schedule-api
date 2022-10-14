pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('deploy') {
            steps {
                sh 'echo "deploy"'
            }
        }
    }
}