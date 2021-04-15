pipeline {
  agent any
  tools {
      maven 'apache-maven-3.6.3'
      jdk 'openjdk-11'
  }
  stages {
    stage("Building Application") {
      steps {
        script {
            def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
            echo version
        }
        sh "mvn install -DskipTests=true"
      }
    }
    stage("Running Unit Tests") {
      steps {
        sh "mvn test"
      }
    }
    stage("SonarQube Analysis") {
      steps {
        echo 'deploying the aplication...'
      }
    }
    stage("Build/Push Docker Image AWS ECR") {
      steps {
        echo 'Build/Push Docker Image ECR...'
      }
    }
    stage("Deploy DEV") {
      when {triggeredBy 'UserIdCause'}
      steps {
        echo 'Deploying DEV'
      }
    }
  }
}