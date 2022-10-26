def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t xlohitj/my-repo:jma-3.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push xlohitj/my-repo:jma-3.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
    def dockerCmd = 'docker run -d -p 8080:8080 xlohitj/my-repo:jma-3.0'
    sshagent(['ec2-server-key'])
        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.221.155.36 ${dockerCmd}"
} 

return this