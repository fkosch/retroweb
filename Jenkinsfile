timeout(30) {
	node {
		def remote = [:]
  		remote.name = '141.37.122.35'
 		remote.host = '141.37.122.35'
 		remote.user = 'sonar'
 		remote.identityFile = '/var/jenkins_home/secrets/id_rsa'
 		remote.passphrase = 'sonar'
		remote.knownHosts = '/var/jenkins_home/secrets/known_hosts'
		
		properties: [
			disableConcurrentBuilds()
		]
		
		jdk = tool name: 'open-jdk-13.0.2'
		env.JAVA_HOME = "${jdk}"
	
		stage('Checkout'){
			deleteDir() //cleanup workspace
            checkout scm
		}
		stage("Build/Test"){
			sh "chmod +x ./gradlew"
			sh "./gradlew clean build"
			step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
		}
		
		stage('SonarQube analysis') {
			withSonarQubeEnv('SonarServer') {
			// requires SonarQube Scanner for Gradle 2.1+
			// It's important to add --info because of SONARJNKNS-281
			sh './gradlew --info sonarqube'
			}
		}
		
		stage('JaCoCo Test Coverage') {
			step([$class: 'JacocoPublisher', 
      			classPattern: 'build/classes',
      			sourcePattern: 'src/main/java',
      			exclusionPattern: 'src/test*'
			])
		}

		stage("Deploy"){
			if(env.BRANCH_NAME == 'master'){
				lock('FitNesse') {
					echo 'Deploy master on PROD-Server ...'
					echo 'Stop server ...'
					sshCommand remote: remote, command: "cd /opt/tomcat;docker-compose stop"
					echo 'Deploy web archive ...'
					sshRemove remote: remote, path: '/data/tomcat/tomcat/data/retroweb.war', failOnError: false
					sshRemove remote: remote, path: '/data/tomcat/tomcat/data/retroweb', failOnError: false
					sshPut remote: remote, from: 'build/libs/retroweb.war', into: '/data/tomcat/tomcat/data/', failOnError: true
					echo 'Start server ...'
					sshCommand remote: remote, command: "cd /opt/tomcat;docker-compose start", failOnError: true
				}
			}
			if(env.BRANCH_NAME == 'develop'){
				echo 'No deployment because checkin was performed to develop branch.'
			}
		}
		
		stage("UI Test") {
			timeout(time: 10, unit: 'MINUTES') {
				if(env.BRANCH_NAME == 'master'){
					sleep time: 4, unit: 'MINUTES' //Delay for FitNesse-Tests   
					lock('FitNesse') {
						step([
							$class: 'FitnesseBuilder',
							options: [
								"fitnesseStart": "false",
								"fitnesseHost": "141.37.122.35",
								"fitnessePortRemote": "8085",
								"fitnesseTargetPage": "TestSuite.TestsuiteRetroweb",
								"fitnesseTargetIsSuite": "true",
								"fitnesseHttpTimeout": "3600000",
								"fitnesseTestTimeout": "3600000",
								"fitnessePathToXmlResultsOut": "FitnesseResults.xml",
							]
						])
						step([
							$class: 'FitnesseResultsRecorder',
							fitnessePathToXmlResultsIn: 'FitnesseResults.xml'
						])
					}
				} 
			}     
		}
	}
}