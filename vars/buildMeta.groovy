#!groovy

// Provide some handy functions under the `gitMeta` namespace to access version
// metadata about the checked-out git version.
//
// Usually you'll just want to use `gitMeta.version()`.

def ref() {
    return sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
}

def date(String ref) {
    ts = sh(script: "git show -s --format=%ct '${ref}'", returnStdout: true).trim()
    return new Date((ts as long) * 1000)
}

def dateString(String ref) {
    return date(ref).format('yyyyMMdd', TimeZone.getTimeZone('UTC'))
}

def dirty() {
    // Ensure git index is up-to-date first. This usually isn't necessary, but
    // can be needed inside a docker container where the index is out of date.
    sh(script: 'git update-index -q --refresh')
    dirtyTree = sh(script: 'git diff-files --quiet', returnStatus: true) != 0
    dirtyIndex = sh(script: 'git diff-index --quiet --cached HEAD', returnStatus: true) != 0
    return (dirtyTree || dirtyIndex)
}

def version() {
    verRef = ref()
    verDate = dateString(verRef)
    verDirty = dirty()
    return "${verDate}+g${verRef}${verDirty ? '.dirty' : ''}"
}
