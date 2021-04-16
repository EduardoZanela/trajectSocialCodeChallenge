def isBuildAReplay() {
  // https://stackoverflow.com/questions/51555910/how-to-know-inside-jenkinsfile-script-that-current-build-is-an-replay/52302879#52302879
  def replyClassName = "org.jenkinsci.plugins.workflow.cps.replay.ReplayCause"
  !currentBuild.rawBuild.getCauses().any{ cause -> cause.toString().contains(replyClassName) }
}
def isDeployDevCommit(){
  def message = sh(returnStdout: true, script: 'git log --format=format:"%s %b" -1 ${GIT_COMMIT}')
  def changeId = env.CHANGE_ID
  message.contains('#deploydev') &&  !isPullRequest()
}
def isPullReques(){
  def changeId = env.CHANGE_ID
  changeId?.trim()
}
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
            echo env.CHANGE_BRANCH
            echo env.CHANGE_TARGET
            echo env.CHANGE_ID
            
            sh 'printenv'
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
        script {
            def causes = currentBuild.rawBuild.getCauses()
            println "Root cause : " + currentBuild.toString()
            for(cause in causes) {
                if (cause.class.toString().contains("UpstreamCause")) {
                    println "This job was caused by job " + cause.upstreamProject
                } else {
                    println "Root cause : " + cause.toString()
                }
            }
        }    
      }
    }
    stage("Deploy DEV") {
      when {expression {isDeployDevCommit()}}
      steps {
        echo 'Deploying DEV'
      }
    }
    stage("Deploy PROD") {
      when {branch 'main'}
      steps {
        echo 'Deploying PROD'
      }
    }
  }
}
//allOf { triggeredBy 'UserIdCause'}