[Config Home](config.md) | [Docs Home](../../index.md)

### &lt;daogen-config/&gt; reference <a name="top"/>

This is the reference for 'daogen-config' element configuration and its attributes.

<table width="100%">
	<tr>
		<th colspan="2">daogen-config attributes quick reference</th>
	</tr>
	<tr>
		<th width="30%">Attribute</th>
		<th width="70%">Description</th>
	</tr>
	<tr>
		<td><a href="#base-src-folder">base-src-folder</a></td>
		<td>The base source folder to use for generating the code</td>
	</tr>
	<tr>
		<td><a href="#database-type">database-type</a></td>
		<td>The database product used</td>		
	</tr>	
	<tr>
		<td><a href="#gen-version">gen-version</a></td>
		<td>The version that will be written in generated code content.</td>
	</tr>
	<tr>
		<td><a href="#gen-author">gen-author</a></td>
		<td>The author that will be written in generated code content.</td>
	</tr>
	<tr>
		<td><a href="#src-main-java">src-main-java</a></td>
		<td>Relative path to ${base-src-folder} for java resources.</td>
	</tr>
	<tr>
		<td><a href="#src-test-java">src-test-java</a></td>
		<td>Relative path to ${base-src-folder} for test java resources.</td>
	</tr>
	<tr>
		<td><a href="#src-main-resources">src-main-resources</a></td>
		<td>Relative path to ${base-src-folder} for resources.</td>
	</tr>
	<tr>
		<td><a href="#src-test-resources">src-test-resources</a></td>
		<td>Relative path to ${base-src-folder} for test resources.</td>
	</tr>
	<tr>
		<td><a href="#src-doc-openapi">src-doc-openapi</a></td>
		<td>Src folder for YAML openapi helper definitions.</td>
	</tr>
	<tr>
		<td><a href="#src-helpers">src-helpers</a></td>
		<td>Folder for helper classes (default is the same as ${src-main-java})</td>
	</tr>
	<tr>
		<td><a href="#class-config">class-config</a></td>
		<td>Basic class for generating code</td>
	</tr>
	<tr>
		<td><a href="#type-mapper">type-mapper</a></td>
		<td>The java2sql TypeMapper</td>
	</tr>
	<tr>
		<td><a href="#type-map-config">type-map-config</a></td>
		<td>The mapping for java types</td>
	</tr>
	<tr>
		<td><a href="#generator-catalog">generator-catalog</a></td>
		<td>List o fenerators to use</td>
	</tr>
	<tr>
		<td><a href="#decorator-catalog">decorator-catalog</a></td>
		<td>List of decorators to use</td>
	</tr>		
	<tr>
		<td><a href="#default-column-time-insert">default-column-time-insert</a></td>
		<td>Column name for the creation time of the entity</td>
	</tr>
	<tr>
		<td><a href="#default-column-time-update">default-column-time-update</a></td>
		<td>Column name for the last update time of the entity</td>
	</tr>	
	<tr>
		<td><a href="#struct-prefix">struct-prefix</a></td>
		<td>Prefix for data base user type (struct)</td>
	</tr>	
	<tr>
		<td><a href="#package-model">package-model</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-helper">package-helper</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-struct">package-struct</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-rse">package-rse</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-facade-def">package-facade-def</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-facade-data-impl">package-facade-data-impl</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#package-rest-load">package-rest-load</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#factory-def">factory-def</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#factory-data-impl">factory-data-impl</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#base-rest-service">base-rest-service</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#openapi_host">openapi_host</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#openapi_path">openapi_path</a></td>
		<td></td>
	</tr>
	<tr>
		<td><a href="#default-sequence"></a></td>
		<td>default-sequence</td>
	</tr>			
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="base-src-folder">base-src-folder</a></caption>
	<tr>
		<th>Default</th>
		<td>. (current folder)</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>../fj-daogen-sample</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="database-type">database-type</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>oracle, postgres, mysql, db2<</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="gen-version">gen-version</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>1.0 (2020-11-10)</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="gen-author">gen-author</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>John Smith - jsmith@mail.com</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src-main-java">src-main-java</a></caption>
	<tr>
		<th>Default</th>
		<td>${base-src-folder}/src/main/java</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src-test-java">src-test-java</a></caption>
	<tr>
		<th>Default</th>
		<td>${base-src-folder}/src/test/java</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src-main-resources">src-main-resources</a></caption>
	<tr>
		<th>Default</th>
		<td>${base-src-folder}/src/main/resources</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src/test/resources">src/test/resources</a></caption>
	<tr>
		<th>Default</th>
		<td>${base-src-folder}/src/test/resources</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src-doc-openapi">src-doc-openapi</a></caption>
	<tr>
		<th>Default</th>
		<td>${base-src-folder}/src/main/doc/openapi_rest</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="src-helpers">src-helpers</a></caption>
	<tr>
		<th>Default</th>
		<td>SRC-MAIN-JAVA</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="class-config">class-config</a></caption>
	<tr>
		<th>Default</th>
		<td>cl://config/daogen_default_class_config.xml</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="type-mapper">type-mapper</a></caption>
	<tr>
		<th>Default</th>
		<td>org.fugerit.java.daogen.base.config.DaogenTypeMapper</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="type-map-config">type-map-config</a></caption>
	<tr>
		<th>Default</th>
		<td>cl://config/daogen_default_type_mapping.xml</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="generator-catalog">generator-catalog</a></caption>
	<tr>
		<th>Default</th>
		<td>cl://config/default-generator-catalog.xml</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="decorator-catalog">decorator-catalog</a></caption>
	<tr>
		<th>Default</th>
		<td>cl://config/default-decorator-catalog.xml</td>
	</tr>
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="default-column-time-insert">default-column-time-insert</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>creation_time</td>
	</tr>	      
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="default-column-time-update">default-column-time-update</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>update_time</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="struct-prefix">struct-prefix</a></caption>
	<tr>
		<th>Default</th>
		<td>UT_</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>OBJ_</td>
	</tr>    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-model">package-model</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.def.model</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-helper">package-helper</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.helper</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-struct">package-struct</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.struct</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-rse">package-rse</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.rse</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-facade-def">package-facade-def</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.def.facade</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-facade-data-impl">package-facade-data-impl</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.facade.data</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="package-rest-load">package-rest-load</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.rest.load</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="factory-def">factory-def</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="factory-data-impl">factory-data-impl</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>org.fugerit.java.daogen.sample.impl.facade.data.FugeritDataLogicFacade</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="base-rest-service">base-rest-service</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td></td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="openapi_host">openapi_host</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>http://localhost:9080</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="openapi_path">openapi_path</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td></td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="default-sequence">default-sequence</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>SEQ_SAMPLE</td>
	</tr>	    
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>
