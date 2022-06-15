def kaodichifunction(String repoUrl){
pipeline{
    agent any
    stages{
        stage('parallel-job'){
            parallel{
                stage('Collect Recent'){
                    echo "Collect recent changes to log files"
                    sh 'cd /var/lib/logs'
                    sh 'ls'
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
