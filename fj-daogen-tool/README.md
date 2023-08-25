# Fugerit Mars DAO Generation (fj-daogen)

## Daogen Sample (fj-daogen-sample)

[back to fj-daogen index](../README.md)  

*Description* :  
Sample tool wrapper for the [DAO Configuration dump](docs/dao_dump.md) and [DAO Generation](docs/dao_gen.md) features.

*Status* :  
All basic features are implemented.

*Since* : fj-doc 1.0.0
  
*Quickstart* : 

### Build the project :

`mvn clean install -P singlepackage`

### For [DAO Configuration dump](../docs/dao_dump.md) 

`java -jar target/dist-fj-daogen-tool-X.X.X.jar --action dump --db-config [database connection confg] --daogen-config [path-to-output-file]`

(For a sample db-config file see [db-config-sample.properties](src/test/resources/db-config-sample.properties)) 


### For [DAO Generation](../docs/dao_gen.md)
`java -jar target/dist-fj-daogen-tool-X.X.X.jar --action daogen --daogen-config [path-to-input-file]`  

(see a [sample daogen-config-xml](src/test/resources/fugerit-daogen-config-sample.xml))


