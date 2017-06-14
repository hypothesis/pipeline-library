#!groovy

// Push a pre-tagged docker build to the Docker Hub, and also update the
// `latest` tag.
def call(Map parameters = [:]) {
    image = parameters.image
    // By default use a blank URL, i.e. the public Docker Hub.
    url = parameters.get('url', '')
    // By default use the default Docker Hub credentials.
    credentialsId = parameters.get('credentialsId', 'docker-hub-build')

    if (!image) {
        error 'you must provide a docker image'
    }

    docker.withRegistry(url, credentialsId) {
        image.push()
        image.push('latest')
    }
}
