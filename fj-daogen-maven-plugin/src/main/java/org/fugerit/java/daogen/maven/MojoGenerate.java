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
 * <p>NOTE: if using eclipse and the build are too slow, considering using the {@link MojoGenerateLazy} plugin,
 * it is the same as this plugin bug run on goal generate-lazy and m2e lifecycle is configured to run only on full builds.</p>
 * 
 */
@Mojo( name = "generate" )
public class MojoGenerate extends AbstractMojoGenerate {
	

}