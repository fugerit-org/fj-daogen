# Mars - Fugerit DAOGEN A.P.I. (fj-daogen)  

Framework for generating model and persistence A.P.I.
Provides tools for dumping configuration from relational database.
A [quickstart](https://github.com/fugerit-org/fj-daogen-quickstart) is available too.

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](https://github.com/fugerit-org/fj-daogen/blob/main/CHANGELOG.md)  
[![docs](https://img.shields.io/badge/docs-mars-cyan.svg)](src/docs/index.md)  
[![license](https://img.shields.io/badge/License-Apache%20License%202.0-teal.svg)](https://opensource.org/licenses/Apache-2.0)  
[![code of conduct](https://img.shields.io/badge/Conduct-Contributor%20Covenant%202.1-purple.svg)](https://github.com/fugerit-org/fj-universe/blob/main/CODE_OF_CONDUCT.md)
[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-base.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-base)  
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_fj-daogen&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit-org_fj-daogen)  
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_fj-daogen&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit-org_fj-daogen) 

[![Java runtime version](https://img.shields.io/badge/run%20on-java%208+-%23113366.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Java build version](https://img.shields.io/badge/build%20on-java%2011+-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-3.9.0+-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)](https://universe.fugerit.org/src/docs/versions/maven3_9.html)
[![Fugerit Github Project Conventions](https://img.shields.io/badge/Fugerit%20Org-Project%20Conventions-1A36C7?style=for-the-badge&logo=Onlinect%20Playground&logoColor=white)](https://universe.fugerit.org/src/docs/conventions/index.html)

Useful resources : [github pages documentation](https://marsdocs.fugerit.org/) | [project home page](https://www.fugerit.org/perm/mars) | docgen [home](https://www.fugerit.org/data/java/doc/mars/index.html) | [release notes](https://www.fugerit.org/data/java/doc/mars/release-notes.html) | [Doagen Maven Plugin Site](https://docs.fugerit.org/data/java/site/fj-daogen-maven-plugin/generate-mojo.html)

[![xsd](https://img.shields.io/badge/daogen%20xsd-daogen%201.0-purple.svg)](https://www.fugerit.org/data/java/doc/xsd/daogen-config-1-0.xsd)
[![xsd](https://img.shields.io/badge/xsd%20reference-daogen%201.0-purple.svg)](https://marsdocs.fugerit.org/src/docs/config/daogen-config-xsd-ref.html)


*About javadoc*  
Javadoc are far from being complete, but you can find latest version at [https://www.fugerit.org](https://www.fugerit.org/data/java/javadoc/)  
Note that, being an open source project hosted on maven central, you can find release javadoc on [javadoc.io](https://javadoc.io/doc/org.fugerit.java/fj-daogen-base/)

*Description*
This project provides code generation API, especially for persistence (DAO). But can be used to generate other item too.
There are two basic feature supplied by the library : 
1. [DAO Configuration dump](src/docs/dao_dump.md), this is optional, it provides a configuration dump for the 'DAO Generation' feature.
2. [DAO Generation](src/docs/dao_gen.md), this is the main feature, it generates various items based on a configuration file, named 'daogen-config.xml' (see a [sample daogen-config-xml](fj-daogen-sample/src/main/daogen/fugerit-sample-daogen-config.xml))

[configuration entry point](https://marsdocs.fugerit.org/src/docs/config/config.html)

## [fj-daogen-base](fj-daogen-base/README.md) the core module
This modules contains core functionalities of the library : 'DAO Configuration dump' and 'DAO Generation'

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-base.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-base)
[![javadoc](https://javadoc.io/badge2/org.fugerit.java/fj-daogen-base/javadoc.svg)](https://javadoc.io/doc/org.fugerit.java/fj-daogen-base)

## [fj-daogen-maven-plugin](fj-daogen-maven-plugin/README.md) a maven plugin for the [fj-daogen-base](fj-daogen-base/README.md) functionalities
This plugin contains simple mojos for handling dao generation and dao configuration dump. ù

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-maven-plugin.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-maven-plugin)
[![javadoc](https://javadoc.io/badge2/org.fugerit.java/fj-daogen-maven-plugin/javadoc.svg)](https://javadoc.io/doc/org.fugerit.java/fj-daogen-maven-plugin)

## [fj-daogen-sample](fj-daogen-sample/README.md) a demo project for [fj-daogen-base](fj-daogen-base/README.md) and [fj-daogen-maven-plugin](fj-daogen-maven-plugin/README.md)
This projects can be used as a demo for the main features (dao generation and configuration dump) of the other modules. 

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-sample.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-sample)
[![javadoc](https://javadoc.io/badge2/org.fugerit.java/fj-daogen-sample/javadoc.svg)](https://javadoc.io/doc/org.fugerit.java/fj-daogen-sample)

## [fj-daogen-tool](fj-daogen-tool/README.md) tool wrapper
Simple tool wrapper for dao generation and configuration dump features. 

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-tool.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-tool)
[![javadoc](https://javadoc.io/badge2/org.fugerit.java/fj-daogen-tool/javadoc.svg)](https://javadoc.io/doc/org.fugerit.java/fj-daogen-tool)