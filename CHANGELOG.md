# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- tag element (HEAD) to scm element. (pom.xml)
- issueManagement element (pom.xml, url : https://github.com/fugerit-org/fj-daogen/issues )
- fj-tester-helper8 set to 0.4.1
- [workflow](src/main/md/github/create_maven_build_workflow.md) for package testing and dependency upload

### Changed

- fj-bom set to 1.3.3
- Changelog badge link set absolute 'https://github.com/fugerit-org/fj-daogen/blob/main/CHANGELOG.md'

### Fixed

- scm url (.git was missing at the end).

## [1.1.8] - 2023-09-01

### Added

- documentation badge
- keep a changelog badge
- maven repo central and javadoc badges to modules
- javadoc badges

### Changed

- fj-bom version set to 1.3.1
- Added build metadata to artifacts (https://github.com/fugerit-org/fj-bom/issues/2) (#7)
- Sonar Cloud Maven Build set to use maven profile sonarfugerit and github environmental variable for sonarKey (#9)
- New changelog style based on : [https://github.com/olivierlacan/keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog) (#8)
- badge order on README.md
- directory docs renamed to src/docs (to comply with maven [standard layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html))
- test resource : fugerit-sample-daogen-config.xml renamed to daogenruntest-sample-daogen-config.xml (to make it clear it is only for test run purpose) (#11)
- DaogenCatalogEntity increased test coverage (#11)

### Fixed

- DaogenCatalogEntity now uses equals() method from java.lang.Object (#11)

## [1.1.7] - 2023-09-01

### Removed

- this version is skipped because of a maven release fail.

## [1.1.6 and previous]

### Changed

- only the [release notes](docgen/release-notes.txt) are available.