#!/bin/bash

 pipeline {
     agent any

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
             stage('deploy') {
                 steps {
                     sh "docker compose -f ./deploy/docker/docker-compose-docker.yml up -d"
                     sh "./gradlew :bootRun"
                 }
             }
     }
 }
