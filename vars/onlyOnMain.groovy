#!groovy

/**
 * Execute enclosed code, only if on the main branch.
 *
 * Prints a message to notify when the enclosed code is being skipped on
 * non-main branches.
 */
def call(Closure body) {
    if (env.BRANCH_NAME != 'main') {
        echo "skipping onlyOnMain steps for branch '${env.BRANCH_NAME}'"
        return
    }
    body()
}
