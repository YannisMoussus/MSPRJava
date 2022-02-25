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
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Deploy') {
            steps {
                script {
                    deploy adapters: [
                        tomcat10(
                            credentialsId: '',
                            path: '',
                            url: 'http://172.16.89.68:8081'
                        )
                    ],
                    contextPath: '/pipeline',
                    onFailure: false,
                    war: '*/target/*.war'
                }
            }
        }
    }
}
