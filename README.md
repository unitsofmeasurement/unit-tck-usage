# unit-tck-usage
JSR 363 - TCK Usage Demo Project illustrating how an implementation of the JSR 363 API can be tested using the JSR 363 TCK.

This branch tests [Eclipse UOMo](https://www.eclipse.org/uomo/) running **Java SE 7**.

**Note:** While Eclipse UOMo is still incubating, the modules are only available on a P2 repository/Update site. 
You have to check out and build the right branch (currently **0.7**) of UOMo locally with `mvn clean install` before running the TCK harness.

[![Circle CI](https://circleci.com/gh/unitsofmeasurement/unit-tck-usage.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/unit-tck-usage)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg?style=flat-square)](http://opensource.org/licenses/BSD-3-Clause)

The [TCK](../../../unit-tck) can be started by running it via TestNG (`RunTCKTest` class)