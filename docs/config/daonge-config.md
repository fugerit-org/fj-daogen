<table>
	<tr>
		<th width="25%">Attribute</th>
		<th width="50%">Description</th>
		<th width="25%">Note</th>		
	</tr>
	<tr>
		<td>base-src-folder</td>
		<td>The base source folder to use for generating the code</td>
		<td>es. : ../fj-daogen-sample</td>	
	</tr>
	<tr>
		<td>database-type</td>
		<td>The database product used</td>								
		<td>oracle, postgres, mysql, db2</td>
	</tr>	
	<tr>
		<td>gen-version</td>
		<td>The version that will be written in generated code content.</td>
		<td>Es. 1.0 (2020-11-10)</td>
	</tr>
	<tr>
		<td>gen-author</td>
		<td>The author that will be written in generated code content.</td>
		<td>Es. John Smith - jsmith@mail.com</td>
	</tr>
	<tr>
		<td>src-main-java</td>
		<td>Relative path to ${base-src-folder} for java resources.</td>
		<td>Default : ${base-src-folder}/src/main/java</td>
	</tr>
	<tr>
		<td>src-test-java</td>
		<td>Relative path to ${base-src-folder} for test java resources.</td>
		<td>Default : ${base-src-folder}/src/test/java</td>
	</tr>
	<tr>
		<td>src-main-resources</td>
		<td>Relative path to ${base-src-folder} for resources.</td>
		<td>Default : ${base-src-folder}/src/main/resources</td>
	</tr>
	<tr>
		<td>src-test-resources</td>
		<td>Relative path to ${base-src-folder} for test resources.</td>
		<td>Default : ${base-src-folder}/src/test/resources</td>
	</tr>
	<tr>
		<td>src-doc-openapi</td>
		<td>Src folder for YAML openapi helper definitions.</td>
		<td>default: ${base-src-folder}/src/main/doc/openapi_rest</td>
	</tr>
	<tr>
		<td>src-helpers</td>
		<td>Folder for helper classes (default is the same as ${src-main-java})</td>
		<td>default : SRC-MAIN-JAVA</td>
	</tr>
	<tr>
		<td>class-config</td>
		<td>Basic class for generating code</td>
		<td>default : cl://config/daogen_default_class_config.xml</td>
	</tr>
	<tr>
		<td>type-mapper</td>
		<td>The java2sql TypeMapper</td>
		<td>Default : org.fugerit.java.daogen.base.config.DaogenTypeMapper</td>
	</tr>
	<tr>
		<td>type-map-config</td>
		<td>The mapping for java types</td>
		<td>Default : cl://config/daogen_default_type_mapping.xml</td>
	</tr>
	<tr>
		<td>generator-catalog</td>
		<td>List o fenerators to use</td>
		<td>Default : cl://config/default-generator-catalog.xml</td>
	</tr>
	<tr>
		<td>decorator-catalog</td>
		<td>List of decorators to use</td>
		<td>Default : cl://config/default-decorator-catalog.xml</td>
	</tr>		
	<tr>
		<td>default-column-time-insert</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>default-column-time-update</td>
		<td></td>
		<td></td>
	</tr>	
	<tr>
		<td>struct-prefix</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-model</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-helper</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-struct</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-rse</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-facade-def</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-facade-data-impl</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>package-rest-load</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>factory-def</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>factory-data-impl</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>base-rest-service</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>openapi_host</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>openapi_path</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>default-sequence</td>
		<td></td>
		<td></td>
	</tr>						
</table>