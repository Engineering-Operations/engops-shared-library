def call(String repoUrl){
  def kaodichifunction(){
  echo "Collect recent changes to log files"
  sh 'cd /var/lib/logs'
  sh 'ls'
 }
}
 
