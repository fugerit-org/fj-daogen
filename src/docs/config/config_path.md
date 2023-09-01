[Config Home](config.md) | [Docs Home](../../index.md)

### Abount config path <a name="top"></a>

Some properties like, for instance
[generator-catalog](tag_daogen-config.md#generator-catalog)
or
[decorator-catalog](tag_daogen-config.md#decorator-catalog)
are path that can assume following values :   
cl://path-in-class-loader   
file://path-to-physical-file  

For instance:  
cl://config/default-generator-catalog.xml  
will try to load in the class loader the path :   
config/default-generator-catalog.xml
