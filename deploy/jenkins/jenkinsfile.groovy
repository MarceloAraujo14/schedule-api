#!groovy

node {
   checkout scm
   load "deploy/jenkins/$deployEnv/jenkinsfile.groovy"
}
