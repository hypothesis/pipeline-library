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
 *     `runArgs` (optional): arguments to `docker run`
 */
def call(Map parameters = [:], Closure body) {
    image = parameters.image
    runArgs = parameters.get('runArgs', '')

    if (!image) {
        error 'you must provide a docker image'
    }

    image.inside(runArgs) {
        body()
    }
}
