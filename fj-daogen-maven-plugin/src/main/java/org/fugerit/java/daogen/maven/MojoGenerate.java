package org.fugerit.java.daogen.maven;

import java.io.File;
import java.io.FileInputStream;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.sonatype.plexus.build.incremental.BuildContext;

@Mojo( name = "generate")
public class MojoGenerate extends AbstractMojo {
	
	public static final String PARAM_DAOGEN_CONFIG = "daogen.config";
	
    @Parameter(property = "daogenConfig", required = true, alias = PARAM_DAOGEN_CONFIG )
    private String daogenConfig;
    
    public void execute() throws MojoExecutionException {
    	getLog().info( "using parameter "+PARAM_DAOGEN_CONFIG+" : "+this.daogenConfig );
        File file = new File( this.daogenConfig );
        try {
        	 getLog().info( "daogen config path : "+file.getCanonicalPath() );
             try ( FileInputStream fis = new FileInputStream( file ) ) {
             	DaogenFacade.generate( fis );	
             }
        } catch (Exception e) {
        	throw new MojoExecutionException( "Error generating code : "+e, e );
        }
    }
    
    /**
     * BuildContext for m2e (it's a pass-though straight to the filesystem when
     * invoked from the Maven cli)
     *
     * @component
     */
    private BuildContext buildContext;

    
}