#!/bin/bash

 pipeline {
     agent {docker { image 'node:16-alpine'}}

     environment {
              branch = '$selectBranch'
              deploy = '$deployEnv'
         }

         stages {
             stage('checkout') {
                 steps {
                     sh "git checkout $branch"
                     sh "echo $branch"
                     sh "echo $deploy"
                 }
             }
//             stage('clean') {
//                 steps {
//                     sh 'chmod +x gradlew'
//                     sh './gradlew clean'
//                 }
//             }
//             stage('test') {
//                 steps {
//                     sh './gradlew test'
//                 }
//             }
//             stage('build') {
//                 steps {
//                     sh './gradlew build'
//                 }
//             }
             stage('docker compose') {
                 steps {
                     sh '''
                        docker-compose --version
                        docker --version
                        docker info
                        '''
                 }
             }
     }
 }
