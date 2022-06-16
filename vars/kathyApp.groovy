
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
                      echo 'This is disk space'
                      sh 'df -h'
                    }
                }
                stage('Stage 2'){
                    steps{
                      echo 'This is to make new directory'
                      sh 'mkdir kathy'
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
