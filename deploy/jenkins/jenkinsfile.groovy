#!groovy

node {
   checkout scm
   def base = load "deploy/jenkins/$deployEnv/jenkinsfile.groovy"
   base.startPipe()
}
