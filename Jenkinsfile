def isBuildAReplay() {
  def replyClassName = "org.jenkinsci.plugins.workflow.cps.replay.ReplayCause"
  !currentBuild.rawBuild.getCauses().any{ cause -> cause.toString().contains(replyClassName) }
}
def isDeployDevCommit(){
  def message = sh(returnStdout: true, script: 'git log --format=format:"%s %b" -1 ${GIT_COMMIT}')
  def changeId = env.CHANGE_ID
  message.contains('#deploydev') &&  !isPullRequest()
}
def isPullRequest(){
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
        script {
          withSonarQubeEnv {
            sh 'mvn verify sonar:sonar -DskipTests=true -Dintegration-tests.skip=true -Dmaven.test.failure.ignore=true'
          }
        }
      }
    }
    stage('SonarQube Quality Gate') {
        steps {
            timeout(time: 2, unit: 'MINUTES') {
                retry(3) {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
    stage("Build/Push Docker Image AWS ECR") {
      steps {
        echo 'Build/Push Docker Image ECR...'
      }
    }
    stage("Deploy DEV") {
      when {expression {isDeployDevCommit()}}
      steps {
        echo 'Deploying DEV'
      }
    }
    stage("Deploy TEST") {
      when {expression {isPullRequest()}}
      steps {
        echo 'Deploying TEST'
      }
    }
    stage("Deploy PROD") {
      when {branch 'main'}
      steps {
        echo 'Deploying PROD'
      }
    }
  }
  post {
    always {
      echo 'always'
    }
    failure {
      echo 'failure'
    }
    success {
      echo 'sucess'
    }
  }
}
//allOf { triggeredBy 'UserIdCause'}
//anyOf/allOf/not
/*
 script {
            def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
            echo version
            echo env.CHANGE_BRANCH
            echo env.CHANGE_TARGET
            echo env.CHANGE_ID
            
            sh 'printenv'
        }
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
        withCredentials([usernamePassword(credentials 'credential-id')])

        parameters {
          string(name: 'AA', defaultValue: '', description: '')
          choice(name: 'AA', choices: ['1', '2'], description: '')
          booleanParam(name: 'AA', defaultValue: '', description: '')
        }
        to use param -> params.AA

        scrip {
          def gf = load 'file.groovy'
        }
*/