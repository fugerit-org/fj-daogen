# Mars - Fugerit DAOGEN A.P.I. (fj-daogen)  

Framework for generating model and persistence A.P.I.
Provides tools for dumping configuration from relational database.
A [quickstart](https://github.com/fugerit-org/fj-daogen-quickstart) is available too.

**Note : Starting from version 0.4+, minimum required java version changed to 1.8**

Useful resources : [github pages documentation](https://marsdocs.fugerit.org/) | [project home page](https://www.fugerit.org/perm/mars) | docgen [home](https://www.fugerit.org/data/java/doc/mars/index.html) | [release notes](https://www.fugerit.org/data/java/doc/mars/release-notes.html) | [Doagen Maven Plugin Site](https://docs.fugerit.org/data/java/site/fj-daogen-maven-plugin/generate-mojo.html)

*About javadoc*  
Javadoc are far from being complete, but you can find latest version at [https://www.fugerit.org](https://www.fugerit.org/data/java/javadoc/)  
Note that, being an open source project hosted on maven central, you can find release javadoc on [javadoc.io](https://javadoc.io/doc/org.fugerit.java/fj-daogen-base/)

*Description*
This project provides code generation API, especially for persistence (DAO). But can be used to generate other item too.
There are two basic feature supplied by the library : 
1. [DAO Configuration dump](docs/dao_dump.md), this is optional, it provides a configuration dump for the 'DAO Generation' feature.
2. [DAO Generation](docs/dao_gen.md), this is the main feature, it generates various items based on a configuration file, named 'daogen-config.xml' (see a [sample daogen-config-xml](fj-daogen-sample/src/main/daogen/fugerit-sample-daogen-config.xml))

## [fj-daogen-base](fj-daogen-base/README.md) the core module
This modules contains core functionalities of the library : 'DAO Configuration dump' and 'DAO Generation'

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-base.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-base)

## [fj-daogen-maven-plugin](fj-daogen-maven-plugin/README.md) a maven plugin for the [fj-daogen-base](fj-daogen-base/README.md) functionalities
This plugin contains simple mojos for handling dao generation and dao configuration dump. ù

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-maven-plugin.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-maven-plugin)

## [fj-daogen-sample](fj-daogen-sample/README.md) a demo project for [fj-daogen-base](fj-daogen-base/README.md) and [fj-daogen-maven-plugin](fj-daogen-maven-plugin/README.md)
This projects can be used as a demo for the main features (dao generation and configuration dump) of the other modules. 

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-samplesvg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-sample)

## [fj-daogen-tool](fj-daogen-tool/README.md) tool wrapper
Simple tool wrapper for dao generation and configuration dump features. 

[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-daogen-tool.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-daogen-tool)

## Sonar Cloud quality gate

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=fugerit-org_fj-daogen)](https://sonarcloud.io/summary/new_code?id=fugerit-org_fj-daogen)
