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

        stage('delete images'){
            steps{
                script{
                    try{
                        bat '''
                            minikube -p minikube docker-env --shell cmd > temp.cmd
                            call temp.cmd
                            docker images
                            del temp.cmd

                            docker rmi sender
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
                        minikube -p minikube docker-env --shell cmd > temp.cmd
                        call temp.cmd
                        docker images
                        del temp.cmd

                        docker build -t sender ./sender
                    '''
                    bat '''
                        minikube -p minikube docker-env --shell cmd > temp.cmd
                        call temp.cmd
                        docker images
                        del temp.cmd

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
        stage('update nginx'){
            steps{
                script{
                    bat 'kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.4.0/deploy/static/provider/cloud/deploy.yaml'
                }
            }
        }

        stage('kubectl apply images') {
            steps{
                script{
                    bat '''
                        minikube -p minikube docker-env --shell cmd > temp.cmd
                        call temp.cmd
                        docker images
                        del temp.cmd

                        kubectl apply -f kubernetes.yaml
                    '''
                    sleep 10
                    bat '''
                        kubectl get pods
                    '''
                    bat '''
                        kubectl apply -f ingress.yml
                    '''
                    bat '''
                        kubectl get services
                    '''
                    bat '''
                        kubectl get ingress
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