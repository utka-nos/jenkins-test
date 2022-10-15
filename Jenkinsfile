pipeline{
    agent {
        label 'windows'
    }

    stages{
        stage('ls'){
            steps{
                script{
                    bat 'dir'
                }
            }
        }
        stage('mvn version'){
            steps{
                withMaven(maven: 'mvn-3.8.6'){
                    script{
                        bat 'mvn --version'
                   }
                }
            }
        }
        stage('test'){
            steps{
                script{
                    bat 'mvn --version'
                }
            }
        }
        stage('minikube'){
            steps{
                script{
                    bat 'minikube ip'
                }
            }
        }
        stage('kubectl'){
            steps{
                script{
                    bat 'kubectl get pods'
                }
            }
        }
        stage('docker'){
            steps{
                script{
                    bat 'docker images'
                }
            }
        }
    }

    post{
        cleanup{
            cleanWs()
        }
    }

}