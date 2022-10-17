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
             stage('deploy ec2') {
                 steps {
                     sh "cd deploy/terraform/"
                     sh "terraform apply --auto-approve"
                 }
             }
     }
 }
