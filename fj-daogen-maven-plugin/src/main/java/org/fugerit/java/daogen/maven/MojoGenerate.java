package org.fugerit.java.daogen.maven;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenFacade;

@Mojo( name = "generate" )
public class MojoGenerate extends AbstractMojo {
	
	public static final String PARAM_DAOGEN_CONFIG = "daogen.config";
	
	
	public static final String PARAM_GENERATED_SOURCE_HELPER = "generated.source.helper";
	
	public static final String PARAM_GEN_BASE_DIR = "gen.base.dir";
	
    @Parameter(property = "daogenConfig", required = true, alias = PARAM_DAOGEN_CONFIG )
    private String daogenConfig;

    @Parameter(property = "genBaseDir", required = false, alias = PARAM_GEN_BASE_DIR )
    private String genBaseDir;
    
    @Parameter(property = "generatedSourceHelper", required = false, alias = PARAM_GENERATED_SOURCE_HELPER )
    private String generatedSourceHelper;
    
    private void addProperty( Properties overrideProperties, String key, String value ) {
    	if ( StringUtils.isNotEmpty(value) ) {
    		getLog().info( "override property, key : "+key+", value : "+value );
        	overrideProperties.setProperty(key, value);	
    	}
    }
    
    public void execute() throws MojoExecutionException {
    	getLog().info( "using parameter "+PARAM_DAOGEN_CONFIG+" : "+this.daogenConfig );
        File file = new File( this.daogenConfig );
        try {
        	 getLog().info( "daogen config path : "+file.getCanonicalPath() );
        	 Properties overrideProperties = new Properties();
        	 this.addProperty(overrideProperties, DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER, this.genBaseDir);
        	 this.addProperty(overrideProperties, DaogenCatalogConstants.GEN_PROP_SRC_MVN_GENERATED, this.generatedSourceHelper);
             try ( FileInputStream fis = new FileInputStream( file ) ) {
             	DaogenFacade.generate( fis, overrideProperties );	
             }
        } catch (Exception e) {
        	throw new MojoExecutionException( "Error generating code : "+e, e );
        }
    }

}