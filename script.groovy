def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t 192.168.1.82:8083/java-maven-app:2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin 192.168.1.82:8083"
        sh 'docker push 192.168.1.82:8083/java-maven-app:2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this