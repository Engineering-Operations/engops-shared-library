
def call(String repoUrl){
  pipeline{
    agent any
    stages{
        stage('working-directory'){
            steps{
                echo 'You are currently working in this directory below'
                sh 'pwd'
            }
        }
        stage('parallel-job'){
            parallel{
                stage('Stage 1'){
                    steps{
                      echo 'This will check all running processes'
                      sh 'top'
                    }
                }
                stage('Stage 2'){
                    steps{
                      echo 'The user currently logged in to the system is displayed below'
                      sh 'whoami'
                    }
                }
            }
        }
        stage('code-deploy'){
            steps {
                git branch: 'main',
                url: "${repoUrl}"
            }
        }
    }
}
}
