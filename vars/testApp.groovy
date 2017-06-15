#!groovy

/**
 * Execute test steps within a previously built Docker image.
 *
 * Executes enclosed code (assumed to be test pipeline steps) within a container
 * started from the passed Docker image.
 *
 * Named parameters:
 *
 *     `image` (required): Docker image within which to run enclosed steps
 */
def call(Map parameters = [:], Closure body) {
    image = parameters.image

    if (!image) {
        error 'you must provide a docker image'
    }

    image.inside() {
        body()
    }
}
