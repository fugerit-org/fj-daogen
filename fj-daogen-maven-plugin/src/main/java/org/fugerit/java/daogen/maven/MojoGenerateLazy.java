package org.fugerit.java.daogen.maven;

import org.apache.maven.plugins.annotations.Mojo;

/**
 * 
 * <p>Convenience plugin for the <code>DaogenFacade.generate()</code> method in module 'fj-daogen-base'.</p>
 * 
 * <p>Required parameter are 'daogenConfig' and 'genBaseDir'.</p>
 * 
 * <p>Many parameters override 'daogen-config.xml' general properties
 * (in this case they always have an alias with the same name of the property they override.</p>
 * 
 *  <p>The difference between this and daogen:generate plugin is only that this plugin is configured 
 *  in the eclipse (m2e) lifecycle to not run only during incremental build. (experimental feature)</p>
 *  
 *  <p>NOTE: it can be recommended in some scenarios, like on a workstation not powerful enough.</p>
 *  
 */
@Mojo( name = "generate-lazy" )
public class MojoGenerateLazy extends AbstractMojoGenerate {
	

}