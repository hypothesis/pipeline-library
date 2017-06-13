pipeline library
================

This repository contains procedures used by [Jenkins
Pipeline](https://jenkins.io/doc/book/pipeline/) configuration files (i.e.
`Jenkinsfile`s) in multiple projects at Hypothesis.

Jenkins Pipeline's [shared library
support](https://jenkins.io/doc/book/pipeline/shared-libraries/) allows us to
define and evolve a set of shared utility functions in this repository, and
provides a straightforward way of using those functions in a `Jenkinsfile`.

How to use the functions defined here
-------------------------------------

To use the routines defined in this repository, you must include a `@Library`
declaration at the top of your `Jenkinsfile`:

```groovy
@Library('github.com/hypothesis/pipeline-library@master')
```

This tells the Pipeline executor to use the version of the library found on the
master branch in this repository. You can also pin to a branch, tag, or commit
using the same syntax.

How to add new shared functions
-------------------------------

Global variables and functions (those exposed within dependent `Jenkinsfile`s at
the top scope) are defined in the `vars/` directory, one per file. A "hello
world" example function can be found in `vars/sayHello.groovy`, and would be
used in a `Jenkinsfile` like this:

```groovy
@Library('github.com/hypothesis/pipeline-library@master')

pipeline {
    agent any

    stages {
        stage('hello') {
            steps {
                sayHello('Francesca')
            }
        }
    }
}
```

License
-------

All code contained in this repository is released under the [2-Clause BSD
License](http://www.opensource.org/licenses/BSD-2-Clause), sometimes referred to
as the "Simplified BSD License" or the "FreeBSD License". A copy of the license
text can be found in the included [`LICENSE`](LICENSE) file.
