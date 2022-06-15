def kaodichifunction(String repoUrl){
  echo "Collect recent changes to log files"
  sh 'cd /var/lib/logs'
  sh 'ls'
 }

stage('code-deploy'){
  steps {
     git branch: 'main',
       url: "${repoUrl}"
  }
}

 
