def kaodichifunction(){
  echo "Collect recent changes to log files"
  sh 'cd /var/lib/logs'
  sh 'ls'

 }
 def Blessingfunction(){
  echo "This is to create filename"
  sh 'cat This is a test file for project 10 > team6.txt'
  sh 'cat team6.txt'

 }

