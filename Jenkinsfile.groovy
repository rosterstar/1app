pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rosterstar/1app.git'
            }
        }
        stage('Install dependencies') {
            steps {
                sh 'pip install -r requirements.txt'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                sudo systemctl stop hello || true
                cp -r . /home/vboxuser/hello
                sudo systemctl start hello
                '''
            }
        }
    }
}
