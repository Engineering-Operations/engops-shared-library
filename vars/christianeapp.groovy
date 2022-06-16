
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
                stage('Stage 1'){
                    steps{
                      echo 'this is to check for disk usage'
                      sh 'du -h'
                    }
                }
                stage('Stage 2'){
                    steps{
                      echo'this is to check memory free space in gigabytes'
                      sh 'free -g'
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
