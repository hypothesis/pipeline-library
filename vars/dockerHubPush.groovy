#!groovy

// Push a pre-tagged docker build to the Docker Hub, and also update the
// `latest` tag.
def call(image) {
    // Blank URL uses the public Docker Hub.
    docker.withRegistry(url: '', credentialsId: 'docker-hub-build') {
        image.push()
        image.push('latest')
    }
}
