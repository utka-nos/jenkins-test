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
                    bat 'cd sender'
                    bat 'mvn package'

                    bat 'cd ..'
                    bat 'cd receiver'
                    bat 'mvn package'
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