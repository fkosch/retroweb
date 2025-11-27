pipeline {
	agent any //Jenkins Knoten auf dem der Job laufen soll
 
	environment {
        SONARQUBE_HOME = tool('SonarQube Scanner 4.8') // Name des SonarQube Scanners konfiguriert bei Jenkins Hilfsprogramme
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5')) // Aufräum-Strategie des Builds, hier Log Rotation mit 5 aufbewahrten Builds
    }
    triggers {
        //pollSCM('H/5 * * * *') // Trigger Strategie
        pollSCM('0 06 * * 3')//jeden Mittwoch 06:00 Uhr
    }
	stages {
		stage('Build') {
			steps {
				sh 'chmod +x ./gradlew' // falls gradlew nicht ausführbar ist
				sh './gradlew clean build jacocoTestReport' // build Befehl
			}
		}
		stage('JUnit-Test') {
			steps {
				junit 'build/**/TEST-*.xml' // Veroeffentlichen der JUnit-Testergebnisse
			}
		}
		stage('SonarQube') {
			steps {
				withSonarQubeEnv('SonarQube') { //SonarQube Konfiguration
					sh '${SONARQUBE_HOME}/bin/sonar-scanner ' +
					'-Dsonar.projectKey=retroweb ' +
					'-Dsonar.projectName=retroweb ' +
					'-Dsonar.projectVersion=origin/master ' +
					'-Dsonar.sources=src ' +
					'-Dsonar.inclusions=**/main/java/** ' +
					'-Dsonar.exclusions=**/Test/**,**/bcrypt/** ' +
					'-Dsonar.java.binaries=**/classes/java/main/** ' +
					'-Dsonar.tests=src/test/java/de/htwg/retroweb/controller,src/test/java/de/htwg/retroweb/service ' +
					'-Dsonar.java.test.binaries=**/classes/java/test/** ' +
					'-Dsonar.test.inclusions=**/test/** ' +
					'-Dsonar.java.libraries=build/libs/retroweb.war ' +
					'-Dsonar.junit.reportPaths=build/test-results/test ' + 
					'-Dsonar.coverage.jacoco.xmlReportPaths=reports/jacoco/test/jacocoTestReport.xml ' +
					'-Dsonar.java.coveragePlugin=jacoco ' + // In den gradle.properties muss jacoco.enable aktiviert sein, sonst wird kein jacoco.exec generiert
					'-Dsonar.coverage.exclusions=**/exception/**,**/entities/**,**/repository/**,**/bcrypt/** ' +
					'-Dsonar.dynamicAnalysis=reuseReports ' +
					'-Dsonar.sourceEncoding=UTF-8 '
				}
			}
		}
		stage('Deploy') {
			when {
				branch 'main'
			}
			steps {
				lock('FitNesse') {
					script {
						def remote = [:]
						remote.name = '127.0.0.1'
						remote.host = '127.0.0.1'
						remote.allowAnyHosts = true
						withCredentials([usernamePassword(credentialsId: 'remote', usernameVariable: 'username', passwordVariable: 'password')]) {
							remote.user = userName
							remote.password = password
							echo 'Deploy master on PROD-Server ...'
							sshRemove remote: remote, path: '/data/tomcat/webapps/retroweb.war', failOnError: false
							sshPut remote: remote, from: 'build/libs/retroweb.war', into: '/data/tomcat/webapps/', failOnError: true
						}
					}
				}
			}
		}
		stage("UI Test") {
			options {
				timeout(time: 5, unit: 'MINUTES') 
			}
			when {
				branch 'main'
			}
			steps {
				sleep time: 1, unit: 'MINUTES' //Delay for FitNesse-Tests   
				lock('FitNesse') {
					step([
						$class: 'FitnesseBuilder',
						options: [
							"fitnesseStart": "false",
							"fitnesseHost": "127.0.0.1",
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
	post {
		always {
			script {
				if (getContext(hudson.FilePath)) {
					deleteDir()
				}
			}
		}
	}
}
