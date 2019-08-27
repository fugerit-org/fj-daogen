package org.fugerit.java.daogen.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo( name = "dump")
public class MojoDump extends AbstractMojo {
	
	public static final String PARAM_DAOGEN_CONFIG = "daogen.config.dump";
	
    @Parameter(property = "daogenConfigDump", required = true, alias = PARAM_DAOGEN_CONFIG )
    private String daogenConfigDump;
    
    public void execute() throws MojoExecutionException {
    	getLog().info( "writing dump file "+PARAM_DAOGEN_CONFIG+" : "+this.daogenConfigDump );
        File file = new File( this.daogenConfigDump );
        try {
        	getLog().info( "daogen config dump path : "+file.getCanonicalPath() );
            //FileIO.writeString( "test" , file );
        	getLog().info( "SORRY! This goal does nothing at the moment!" );
        } catch (Exception e) {
        	throw new MojoExecutionException( "Error generating code : "+e, e );
        }
    }

}