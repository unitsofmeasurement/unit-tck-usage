# unit-tck-usage
JSR 363 - TCK Usage Demo Project illustrating how an implementation of the JSR 363 API can be tested using the JSR 363 TCK.

[![Circle CI](https://circleci.com/gh/unitsofmeasurement/unit-tck-usage.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/unit-tck-usage)
[![Stability: Active](https://masterminds.github.io/stability/active.svg)](https://masterminds.github.io/stability/active.html)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg?style=flat-square)](http://opensource.org/licenses/BSD-3-Clause)

The [TCK](../../../unit-tck) can be started by running it via TestNG (`RunTCKTest` class).
The tests currently require Java 8; there is a known failure when using Java 11 for reason
apparently not related to Seashat (a `NullPointerException` in `org.apache.maven.surefire`).

Tests for the following quantities are skipped because Seshat 1.0 does not define any unit for them:

  * `Acceleration`
  * `CatalyticActivity`
  * `RadiationDoseAbsorbed`
  * `RadiationDoseEffective`
  * `Radioactivity`
