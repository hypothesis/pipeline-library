#!groovy

// The ID of the stored credentials for the Docker Hub
def dockerHubCredentialsID = 'docker-hub-build'

// Push a pre-tagged docker build to the Docker Hub, and also update the
// `latest` tag.
def call(image) {
    docker.withRegistry('', dockerHubCredentialsID) {
        image.push()
        image.push('latest')
    }
}
