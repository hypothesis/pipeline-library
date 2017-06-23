#!groovy

/**
 * Deploy a Docker image to a named environment.
 *
 * Triggers a build of the "deployment" job, which oversees releasing a
 * specified version of an application to a named Elastic Beanstalk environment.
 *
 * Named parameters:
 *
 *     `image` (required): Docker image to deploy
 *     `app` (required): application name
 *     `env` (required): environment name (must be 'qa' or 'prod')
 *
 * N.B. The passed Docker image is only used to establish the Docker tag which
 * will be deployed. This function assumes that the image has already been
 * released to the appropriate Docker registry (for example, by using the
 * `releaseApp` function).
 */
def call(Map parameters = [:]) {
    image = parameters.image
    app = parameters.app
    env = parameters.env

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
                       [$class: 'StringParameterValue', name: 'TYPE', value: 'deploy'],
                       [$class: 'StringParameterValue', name: 'APP_DOCKER_VERSION', value: tag],
                       [$class: 'StringParameterValue', name: 'ENV', value: env]])
}
