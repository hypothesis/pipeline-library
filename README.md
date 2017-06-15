pipeline library
================

This repository contains procedures used by [Jenkins
Pipeline](https://jenkins.io/doc/book/pipeline/) configuration files (i.e.
`Jenkinsfile`s) in multiple projects at Hypothesis.

Jenkins Pipeline's [shared library
support](https://jenkins.io/doc/book/pipeline/shared-libraries/) allows us to
define and evolve a set of shared pipeline helpers in this repository, and
provides a straightforward way of using those functions in a `Jenkinsfile`.

How do I use the helpers defined here?
--------------------------------------

To use the helpers defined in this repository, you must include a `@Library`
declaration at the top of your `Jenkinsfile`:

```groovy
@Library('pipeline-library') _
```

This tells the Pipeline executor to use the default version of the library (the
master branch in this repository). You can also pin to a branch, tag, or commit
using an `@version` identifier. For example, to use the `devel` branch:

```groovy
@Library('pipeline-library@devel') _
```

Include the underscore at the end of the line in order to avoid being caught out
when pinning to versions of the library other than the default.

**N.B.** Currently, this library is configured globally in our Jenkins instance
(at `Manage Jenkins → Configure System → Global Pipeline Libraries`). Ideally,
we would use the support for fully-namespaced library definitions such as
`@Library('github.com/hypothesis/pipeline-library')` but there is currently no
way to execute functions from such libraries "unsandboxed," so they are very
limited in their usefulness.

How can I add new shared functions?
-----------------------------------

Global variables and functions (those exposed within dependent `Jenkinsfile`s at
the top scope) are defined in the `vars/` directory, one per file. A "hello
world" example function can be found in `vars/sayHello.groovy`, and would be
used in a `Jenkinsfile` like this:

```groovy
@Library('pipeline-library') _

stage('hello') {
    sayHello('Francesca')
}
```

What helpers are currently defined by this library?
---------------------------------------------------

| Name                                       | Type     | Description                                                                     |
|--------------------------------------------|----------|---------------------------------------------------------------------------------|
| [`buildApp`](vars/buildApp.groovy)         | function | Builds an application Docker image from a Dockerfile.                           |
| [`buildMeta`](vars/buildMeta.groovy)       | variable | Provides access to build version metadata from git.                             |
| [`deployApp`](vars/deployApp.groovy)       | function | Deploy an application to a named deployment environment.                        |
| [`onlyOnMaster`](vars/onlyOnMaster.groovy) | function | Execute pipeline steps only on the `master` branch.                             |
| [`releaseApp`](vars/releaseApp.groovy)     | function | Release a built application Docker image to the Docker Hub (or other registry). |
| [`sayHello`](vars/sayHello.groovy)         | function | Hello, world!                                                                   |
| [`testApp`](vars/testApp.groovy)           | function | Execute pipeline steps within a built application Docker image.                 |

License
-------

All code contained in this repository is released under the [2-Clause BSD
License](http://www.opensource.org/licenses/BSD-2-Clause), sometimes referred to
as the "Simplified BSD License" or the "FreeBSD License". A copy of the license
text can be found in the included [`LICENSE`](LICENSE) file.
