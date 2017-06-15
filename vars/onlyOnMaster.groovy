#!groovy

/**
 * Execute enclosed code, only if on the master branch.
 *
 * Prints a message to notify when the enclosed code is being skipped on
 * non-master branches.
 */
def call(Closure body) {
    if (env.BRANCH_NAME != 'master') {
        echo "skipping onlyOnMaster steps for branch '${env.BRANCH_NAME}'"
        return
    }
    body()
}
