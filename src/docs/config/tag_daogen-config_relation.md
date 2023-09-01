[Config Home](config.md) | [Docs Home](../../index.md)

### &lt;relation/&gt; reference <a name="top"/>

This is the reference for 'relation' element configuration and its attributes.

<table width="100%">
	<tr>
		<th colspan="2">relation attributes quick reference</th>
	</tr>
	<tr>
		<th width="30%">Attribute</th>
		<th width="70%">Description</th>
	</tr>
	<tr>
		<td><a href="#id">id</a></td>
		<td>Id of the telation</td>
	</tr>
	<tr>
		<td><a href="#name">name</a></td>
		<td>Name of the relation (will be used for field name in java model)</td>
	</tr>	
	<tr>
		<td><a href="#from">from</a></td>
		<td>Id of the entity model where the relation will be added</td>
	</tr>	
	<tr>
		<td><a href="#to">to</a></td>
		<td>Id of the entity type that will be used for relation</td>
	</tr>	
	<tr>
		<td><a href="#mode">mode</a></td>
		<td>Relation mode (one or many)</td>
	</tr>	
	<tr>
		<td><a href="#key">key</a></td>
		<td>Fields to use for the foreign key</td>
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
		<td>userToAddress</td>
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
		<td>USER_ADDRESSES</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="from">from</a></caption>
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
	<caption>Attribute <a name="to">to</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>PUBLIC.FUGERIT.ADDRESS</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="mode">mode</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Values</th>
		<td>one | many</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="key">key</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>ID_USER</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>
