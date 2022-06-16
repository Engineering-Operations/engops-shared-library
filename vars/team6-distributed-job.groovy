pipeline{
	agent{
		label {
			label 'slave1'
		}
	}
	stages{
		stage('version-control'){
			steps{
				checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-id', url: 'https://github.com/Engineering-Operations/parallel-multib-pipeline.git']]])
			}
		}
		stage('EngopsSlave1:cpustatus'){
			parallel{
				stage('cpustat'){
					steps{
						echo 'This is checking cpustatus'
						sh 'lscpu'
					}
				}
				stage('mem-g'){
					steps{
						echo 'This is to check memory space in gigabyte'
						sh 'free -g'
					}
				}
			}
		}
		stage('Initializing slave2'){
		  agent{
			label{
				label 'slave2'
			}
		}
		stages{
			stage('EngopsSlave2:DFS & DU'){
				parallel{
					stage('diskspace'){
						steps{
							echo 'This is checking disk free space'
							sh 'df -h'
						}
					}
					stage('diskusage'){
						steps{
							echo 'This is checking diskusage'
							sh 'du -h'
						}
					}
				}
			}
		}

		}
		stage('Initializing slave3'){
			agent{
				label{
					label 'slave3'
				}
			}
			stages{
				stage('EngopsSlave3:Finalstage'){
					parallel{
						stage('presentdir'){
							steps{
								echo 'Display working directory'
								sh 'pwd'
							}
						}
						stage('finalfile'){
							steps{
								echo 'This is the final file'
								sh 'touch final.txt'
							}
						}
					}
				}
				stage('codedeploy'){
					steps{
						echo 'code Initializing complete'
					}
				}
			}
		}
	}
}
