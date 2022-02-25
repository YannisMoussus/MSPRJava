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
        stage('Test') {
            steps {
                writeFile(file: 'status.txt', text: 'It worked!!!!')
            }
        }
        stage('Deploy') {
            steps {
                writeFile(file: 'status.txt', text: 'Deploy accepted!!!')
            }
        } 
    }
}
