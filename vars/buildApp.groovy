#!groovy

// Build a docker image from the current checkout with the specified
// configuration.
def call(Map parameters = [:]) {
    name = parameters.name
    version = buildMeta.version()
    tag = version.replaceAll('\\+', '-')

    if (!name) {
        error 'you must set an image name'
    }

    // Set the build metadata
    currentBuild.displayName = version
    currentBuild.description = "Docker: ${tag}"

    sh "git archive HEAD | docker build -t ${name}:${tag} -"
    return docker.image("${name}:${tag}")
}
