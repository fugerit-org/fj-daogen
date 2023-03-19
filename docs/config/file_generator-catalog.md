[Config Home](config.md) | [Docs Home](../../index.md)

### File : Generator Catalog <a name="top"/>

This is the reference for [generator-catalog](tag_daogen-config.md#generator-catalog) attribute in [daogen-config](tag_daogen-config.md).

Generator catalog must define 2 generator catalogs : 
* Entity Catalog, containing list of generators that will be applied for every entity  (entity_generators or entity_generators_helper)
* Factory Catalog, containing list of generators taht will be applied only once for run (factory_generators or factory_generators_helper)

If daogen-config attribute [src-helpers](tag_daogen-config.md#src-helpers) is definied, generator catalogs used will be :
* entity_generators_helper
* factory_generators_helper
  
otherwise 
* entity_generators
* factory_generators

Generally speaking generators in the catalog will extends [DaogenBasicGenerator](../code/DaogenBasicGenerator.md)

Here is the default genetaor catalog :
[Default Generator Catalog](../../fj-daogen-base/src/main/resources/config/default-generator-catalog.xml)

<table>
	<tr>
		<th colspan="2">Default entity_generators_base Catalog</th>
	</tr>
	<tr>
		<th>ID</th>
		<th>Description</th>
	</tr>
	<tr>
		<td><a href="#ModelGenerator">ModelGenerator</a></td>
		<td>Generator for interface ${Entity}Model</td>
	</tr>
	<tr>
		<td><a href="#HelperGenerator">HelperGenerator</a></td>
		<td>Generator for class ${Entity}Helper, basic implementation of ${Entity}Model</td>
	</tr>	
	<tr>
		<td><a href="#WrapperGenerator">WrapperGenerator</a></td>
		<td>Generator for class ${Entity}Wrapper implementing ${Entity}Model, wrapping another ${Entity}Model</td>
	</tr>		
	<tr>
		<td><a href="#StructGenerator">StructGenerator</a></td>
		<td>Generator for class Ut${Entity} implementing ${Entity}Model, mapping data base user type (struct)</td>
	</tr>	
	<tr>
		<td><a href="#FinderGenerator">FinderGenerator</a></td>
		<td>Generator for class ${Entity}Finder, a simple finder object for ${Entity}</td>
	</tr>	
	<tr>
		<td><a href="#RSEGenerator">RSEGenerator</a></td>
		<td>Generator for class ${Entity}RSE, simple mapper from java.sql.ResultSet record to ${Entity}Model</td>
	</tr>
	<tr>
		<td><a href="#DocOpenAPIRestGenerator">DocOpenAPIRestGenerator</a></td>
		<td>Gemeratpr fpr sample ${Entity}.yaml to use as basic definition for rest services</td>
	</tr>														
</table>

<br/><a href="#top">top</a><br/>

<table>
	<tr>
		<th colspan="2">Default entity_generators_helper Catalog</th>
	</tr>
	<tr>
		<th>ID</th>
		<th>Description</th>
	</tr>
	<tr>
		<td><a href="#FacadeDefHelperGenerator">FacadeDefHelperGenerator</a></td>
		<td>Generator for interface ${Entity}FacadeHelper (overwritten at every generation), helper interface for ${Entity}Model persistance</td>
	</tr>
	<tr>
		<td><a href="#FacadeDefRealGenerator">FacadeDefRealGenerator</a></td>
		<td>Generator for interface ${Entity}Facade, interface for ${Entity}Model persistance</td>
	</tr>
	<tr>
		<td><a href="#FacadeImplDataHelperGenerator">FacadeImplDataHelperGenerator</a></td>
		<td>Generator for class Data${Entity}FacadeHelper (overwritten at every generation), helper class for ${Entity}Model persistance</td>
	</tr>	
	<tr>
		<td><a href="#FacadeImplDataRealGenerator">FacadeImplDataRealGenerator</a></td>
		<td>Generator for class Data${Entity}Facade, class for ${Entity}Model persistance</td>
	</tr>	
	<tr>
		<td><a href="#RestLoadHelperGenerator">RestLoadHelperGenerator</a></td>
		<td>Generator for class Load${Entity}Helper (overwritten at every generation), helper rest service for ${Entity}Model</td>
	</tr>	
	<tr>
		<td><a href="#RestLoadRealGenerator">RestLoadRealGenerator</a></td>
		<td>Generator for class Load${Entity}, rest service for ${Entity}Model</td>
	</tr>																
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="ModelGenerator">ModelGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.ModelGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-model</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Resources</th>
		<td>
			<a href="../../fj-daogen-base/src/main/java/org/fugerit/java/daogen/base/gen/ModelGenerator.java" target="source">src</a> -
			<a href="https://www.fugerit.org/data/java/javadoc/fj-daogen-base/org/fugerit/java/daogen/base/gen/ModelGenerator.html" target="javadoc">javadoc</a>
		</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="HelperGenerator">HelperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.HelperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-helper</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="WrapperGenerator">WrapperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.WrapperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-helper</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="StructGenerator">StructGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.StructGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-struct</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="FinderGenerator">FinderGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.FinderGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-facade-def<</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="RSEGenerator">RSEGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.RSEGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-rse</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="DocOpenAPIV3RestGenerator">DocOpenAPIV3RestGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.DocOpenAPIV3RestGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>src-doc-openapi</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_base</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="FacadeDefHelperGenerator">FacadeDefHelperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.FacadeDefHelperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-facade-def</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="FacadeDefRealGenerator">FacadeDefRealGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.FacadeDefRealGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-facade-def</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="FacadeImplDataHelperGenerator">FacadeImplDataHelperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.FacadeImplDataHelperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-facade-data-impl</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="FacadeImplDataHelperGenerator">FacadeImplDataHelperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.FacadeImplDataHelperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-facade-data-impl</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="RestLoadHelperGenerator">RestLoadHelperGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.RestLoadHelperGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-rest-load</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Generator <a name="RestLoadRealGenerator">RestLoadRealGenerator</a></caption>
	<tr>
		<th>Type</th>
		<td>org.fugerit.java.daogen.base.gen.helper.RestLoadRealGenerator</td>
	</tr>
	<tr>
		<th>Check value</th>
		<td>package-rest-load</td>
	</tr>	
	<tr>
		<th>Group</th>
		<td>entity_generators_helper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

