pipeline {
  agent any
  tools {
      maven 'apache-maven-3.6.3'
      jdk 'openjdk-11'
  }

  stages {
    stage("build") {
      steps {
        script {
            def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
            echo version
        }
        sh "mvn install -DskipTests=true"
      }
    }
    stage("test") {
      steps {
        sh "mvn test"
      }
    }
    stage("deploy") {
      steps {
        echo 'deploying the aplication...'
      }
    }
  }
}