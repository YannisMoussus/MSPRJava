pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -f /var/lib/jenkins/workspace/MSPRJava_main/ProjetMSPR-main/pom.xml -B -DskipTests clean package' 
            }
        }
    }
}
