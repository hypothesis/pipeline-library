#!groovy

// Execute tests within a docker image previously built by `buildApp`.
def call(Map parameters = [:], Closure body) {
    image = parameters.image

    if (!image) {
        error 'you must provide a docker image'
    }

    image.inside() {
        body()
    }
}
