pipeline {
  agent any
  stages {
    stage('1') {
      steps {
        sleep 1
        bat(script: 'mvn test', encoding: 'utf-8')
      }
    }
  }
}