[Config Home](config.md) | [Docs Home](../../index.md)

### &lt;field/&gt; reference <a name="top"/>

This is the reference for 'field' element configuration and its attributes.

<table width="100%">
	<tr>
		<th colspan="2">field attributes quick reference</th>
	</tr>
	<tr>
		<th width="30%">Attribute</th>
		<th width="70%">Description</th>
	</tr>
	<tr>
		<td><a href="#id">id</a></td>
		<td>Id of the field</td>
	</tr>	
	<tr>
		<td><a href="#nullable">nullable</a></td>
		<td>'yes if nullable, 'no' otherwise</td>
	</tr>
	<tr>
		<td><a href="#size">size</a></td>
		<td>Size of the field</td>
	</tr>
	<tr>
		<td><a href="#sqlType">sqlType</a></td>
		<td>Sql type of the field (as of java.sql.Types)</td>
	</tr>
	<tr>
		<td><a href="#sqlTypeName">sqlTypeName</a></td>
		<td>Sql type name (as returned from metadata)</td>
	</tr>			
	<tr>
		<td><a href="#javaType">javaType</a></td>
		<td>Java type to use for the field</td>
	</tr>
	<tr>
		<td><a href="#structType">structType</a></td>
		<td>Java type to use for the user type (optional)</td>
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
		<td>USERNAME</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="nullable">nullable</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Values</th>
		<td>yes|no</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="size">size</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>8</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="sqlType">sqlType</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>12</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="sqlTypeName">sqlTypeName</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>VARCHAR</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="javaType">javaType</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>java.lang.String</td>
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
		<td>User chosen name</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>

<br/><a href="#top">top</a><br/>

<table>
	<caption>Attribute <a name="structType">structType</a></caption>
	<tr>
		<th>Default</th>
		<td>no default</td>
	</tr>
	<tr>
		<th>Example</th>
		<td>some.package.UtUsername</td>
	</tr>	
	<tr>
		<th>Since</th>
		<td>0.4.1.11</td>
	</tr>
</table>