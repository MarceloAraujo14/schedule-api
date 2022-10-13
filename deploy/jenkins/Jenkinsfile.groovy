
pipeline {
    agent("java")
    stages {
        stage('clean') {
            steps {
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