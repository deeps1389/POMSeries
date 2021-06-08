pipeline { 
agent any 
    stages { 
        
        stage ('Build') { 
            steps{
                echo "Building"

            }
        }
 //for windows ---> bat "mvn clean install"   
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean install"
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        
        
        
    }

 }
