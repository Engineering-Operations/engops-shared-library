def call(String repoUrl){
  pipeline{
    agent any
    stages{
        stage('version-control'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-id', url: 'https://github.com/Engineering-Operations/parallel-multib-pipeline.git']]])
            }
        }
        stage('parallel-job'){
            parallel{
                stage('syscheck'){
                    steps{
                      echo 'This is to check for operating system statistics'
                      sh 'cat /etc/os-release'
                    }
                }
                stage('jenkins-stat'){
                    steps{
                      echo 'This is to check the current status of jenkins'
                      sh 'sudo systemctl status jenkins'
                    }
                }
            }
        }
        stage('parallel-job2'){
            parallel{
               stage('disk-free'){
                    steps{
                      echo 'This is to check disk free space'
                      sh 'df -h'
                    }
               }
                stage('disk-usage'){
                    steps{
                      echo 'this is to check disk usage'
                      sh 'du -h'
                    }
               }
                stage('check memory free space in megabytes'){
                    steps{
                      echo 'this is to check memory free space megabytes'
                      sh 'free -m'
                    }
                }
            }   
        }
        stage('code-deploy'){
            steps {
                echo 'hello world'
            }
        }
    }
}
}
