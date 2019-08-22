package org.fugerit.java.daogen.sample.web.config;

import java.util.HashSet;
import java.util.Set;

public class DaogenSample extends javax.ws.rs.core.Application {

	   public Set<Class<?>> getClasses() {
		      Set<Class<?>> s = new HashSet<Class<?>>();
		      s.add(org.fugerit.java.daogen.sample.impl.rest.load.LoadAddress.class);
//		      s.add(org.fugerit.java.daogen.sample.impl.rest.load.LoadUser.class);
//		      s.add(org.fugerit.java.daogen.sample.impl.rest.load.LoadLogData.class);
//		      s.add(org.fugerit.java.daogen.sample.impl.rest.load.LoadUpload.class);
//		      s.add(org.fugerit.java.daogen.sample.impl.rest.load.LoadTestTwoFieldKey.class);
		      return s;
		}
	   
}