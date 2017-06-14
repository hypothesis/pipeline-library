#!groovy

// A helper that executes its closure only on the master branch.
def call(Closure body) {
    if (env.BRANCH_NAME != 'master') {
        echo "skipping onlyOnMaster steps for branch '${env.BRANCH_NAME}'"
        return
    }
    body()
}
