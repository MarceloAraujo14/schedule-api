
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
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('buildImage') {
            steps {
                sh './gradlew :bootBuildImage'
            }
        }
        stage('buildImage') {
            steps {
                sh 'cd deploy/docker'
                sh 'docker-compose up -d'
            }
        }
    }
}