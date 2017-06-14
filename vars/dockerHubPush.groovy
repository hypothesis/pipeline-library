#!groovy

// Push a pre-tagged docker build to the Docker Hub, and also update the
// `latest` tag.
def call(image) {
    // Blank URL uses the public Docker Hub.
    url = ''
    credentialsId = 'docker-hub-build'
    docker.withRegistry(url, credentialsId) {
        image.push()
        image.push('latest')
    }
}
