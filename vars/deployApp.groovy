#!groovy

// Deploy a named docker image to a specified environment.
def call(Map parameters = [:]) {
    app = parameters.app
    env = parameters.env
    image = parameters.image

    if (!image) {
        error 'you must provide a docker image'
    }
    if (!app) {
        error 'you must provide an application name'
    }
    if (env != 'qa' && env != 'prod') {
        error 'you must provide a valid environment name'
    }

    imageName = image.imageName()
    tagIdx = imageName.lastIndexOf(':')
    if (tagIdx == -1) {
        error 'the provided image must be tagged with a version'
    }
    tag = imageName.substring(tagIdx + 1)

    build(job: 'deployment',
          parameters: [[$class: 'StringParameterValue', name: 'APP', value: app],
                       [$class: 'StringParameterValue', name: 'TYPE', value: 'exact-version'],
                       [$class: 'StringParameterValue', name: 'APP_DOCKER_VERSION', value: tag],
                       [$class: 'StringParameterValue', name: 'ENV', value: env]])
}
