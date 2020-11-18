[Config Home](config.md) | [Docs Home](../../index.md)

### &lt;entity/&gt; reference <a name="top"/>

This is the reference for 'entity' element configuration and its attributes.

<table width="100%">
	<tr>
		<th colspan="2">entity attributes quick reference</th>
	</tr>
	<tr>
		<th width="30%">Attribute</th>
		<th width="70%">Description</th>
	</tr>
	<tr>
		<td><a href="#id">id</a></td>
		<td>Id of the entity (usually [[${catalog}].${schema}].${name}</td>
	</tr>
	<tr>
		<td><a href="#catalog">catalog</a></td>
		<td>Catalog of the entity</td>
	</tr>	
	<tr>
		<td><a href="#schema">schema</a></td>
		<td>Schema of the entity</td>
	</tr>		
	<tr>
		<td><a href="#name">name</a></td>
		<td>Name of the entity</td>
	</tr>
	<tr>
		<td><a href="#primaryKey">primaryKey</a></td>
		<td>Field(s comma separated) included in the primary key</td>
	</tr>	
	<tr>
		<td><a href="#comments">comments</a></td>
		<td>Comments to the entity (will be included in java comments)</td>
	</tr>	
	<tr>
		<td><a href="#sequenceName">sequenceName</a></td>
		<td>Sequence used for the entity (If present, overrides <a href="tag_daogen-config.html#default-sequence">Attribute default-sequence in daogen-config</a>)</td>
	</tr>	
	<tr>
		<td><a href="#facadeMode">facadeMode</a></td>
		<td>Will be used by facade generators for creating the java artifacts</td>
	</tr>		
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="id">id</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>PUBLIC.FUGERIT.USER</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="catalog">catalog</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>PUBLIC</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="schema">schema</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>FUGERIT</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="name">name</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>USER</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="comments">comments</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>Contains users data</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="sequenceName">sequenceName</a></caption>
	<tr>
		<th>Default</th>
		<td><a href="tag_daogen-config.html#default-sequence">Attribute default-sequence in daogen-config</a></td>
	</tr>
	<tr>
		<th>Example</th>
		<td>SEQ_USER</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="facadeMode">facadeMode</a></caption>
	<tr>
		<th>Default</th>
		<td>complete</td>
	</tr>
	<tr>
		<th>Values</th>
		<td>
			<ul>
			<li>complete</li>
			<li>select</li>
			<li>insert</li> 
			<li>delete</li>
			<li>update</li>
			<li>none</li>
			</ul>
		</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>
