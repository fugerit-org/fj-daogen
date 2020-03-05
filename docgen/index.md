*Page last updated on* **2020-03-05**

[Next](qs_install.html)

Mars (a.k.a. fj-daogen) is a framework for generating model and persistence A.P.I. Provides tools for dumping configuration from relational database.  

In brief Mars allow to  :
- Define a configuration model in its own format (daogen.xml)
- Generate the java model and data access objects

Optionally is possible to : 
- Dump the configuration directly from an existing data source
- Customize the generated artifacts for your need

Even though there are plenty of framework for handling persistence in java, 
the last point is the key reason why Mars was developed, to keep more control 
on the generated artifacts and modify it at a lower (less abstract) level.  
*Currently most persistence framework tries to keep the user as far as  possible from implementation details, while Mars let the developer  a lot of control over the implementation*.  
Of course, depending on your profile and purpose, this could be either a good or bad feature, as it comes at the cost of losing some simplicity. 

Requirements : 
* Java 1.7+
* [Jupiter (Fugerit Core A.P.I.)](https://www.fugerit.org/perm/jupiter)

Some useful resources : 
* [Mars Git Repository](https://github.com/fugerit-org/fj-daogen)
* [Quickstart Maven project](https://github.com/fugerit-org/fj-daogen-quickstart)

NOTE : Especially Quickstart Maven Project is an expanded 'playable' version of this quickstart.
