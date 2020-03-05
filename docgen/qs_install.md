*Page last updated on* **2020-03-05**

[Previous](index.html) | [Next](qs_dump.html)

# 1. Installing using maven #

If you are working with a maven project Mars installation is as simple as adding the maven dependancy to your project : 

```
<dependency>
	<groupId>org.fugerit.java</groupId>
	<artifactId>fj-daogen-base</artifactId>
	<version>${fj-daogen-version}</version>
</dependency>
```

Of course you may need other dependencies, for instance the database driver (in this example postgres) : 

```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>${postgres-driver-version}</version>
</dependency>
```

# 2. Manual install #

If you prefer to manually load all needed libraries you can get them using git and maven : 

```
git clone https://github.com/fugerit-org/fj-daogen.git
cd fj-daogen
mvn clean install -P dep
```

All the dependancies will be in folder :  
fj-daogen-base/target/dependancy/lib
 
the main artifact will be in :  
fj-daogen-base/target/fj-daogen-base-${version}.jar