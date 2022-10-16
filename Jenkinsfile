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
        stage('build'){
            steps{
                script{
                    bat '''
                        cd sender
                        mvn package
                    '''
                    bat '''
                        cd receiver
                        mvn package
                    '''
                }
            }
        }
        stage('build images'){
            steps{
                script{
                    bat '''
                        docker rmi sender
                    '''
                    bat '''
                        docker rmi receiver
                    '''
                    bat '''
                        docker build -t sender ./sender
                    '''
                    bat '''
                        docker build -t receiver ./receiver
                    '''
                }
            }
        }
        stage('test images'){
            steps{
                script{
                    bat '''
                        docker images
                    '''
                }
            }
        }
        stage('kubectl apply images') {
            steps{
                script{
                    bat '''
                        kubectl apply -f kubernetes.yaml
                    '''
                    bat '''
                        kubectl get pods
                    '''
                    bat '''
                        kubectl get services
                    '''
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