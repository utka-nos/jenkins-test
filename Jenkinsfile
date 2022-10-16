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
        stage('setting docker env') {
            steps{
                script{
                    bat 'minikube -p minikube docker-env --shell cmd > temp.cmd'
                    bat 'call temp.cmd'
                    bat 'del temp.cmd'

                    bat 'docker images'
                }
            }
        }
        stage('delete images'){
            steps{
                script{
                    try{
                        bat '''
                            docker rmi sender
                        '''
                        bat '''
                            docker rmi receiver
                        '''
                    }
                    catch (err){
                        echo err.getMessage()
                    }
                }
            }
        }
        stage('build images'){
            steps{
                script{

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
                    sleep 10
                    bat '''
                        kubectl get pods
                    '''
                    bat '''
                        kubectl get services
                    '''
                }
            }
        }

        stage('unsetting docker env') {
            steps{
                script{
                    bat 'minikube -p minikube docker-env -u --shell cmd > temp.cmd'
                    bat 'call temp.cmd'
                    bat 'del temp.cmd'
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