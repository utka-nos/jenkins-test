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
                        cd ../receiver
                        mvn package
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