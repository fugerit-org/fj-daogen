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

/**
 * 
 * <p>Convenience plugin for the <code>DaogenFacade.generate()</code> method in module 'fj-daogen-base'.</p>
 * 
 * <p>Required parameter are 'daogenConfig' and 'genBaseDir'.</p>
 * 
 * <p>Many parameters override 'daogen-config.xml' general properties
 * (in this case they always have an alias with the same name of the property they override.</p>
 * 
 */
@Mojo( name = "generate" )
public class MojoGenerate extends AbstractMojo {
	
	public static final String PARAM_DAOGEN_CONFIG = "daogen.config";
	
	
	public static final String PARAM_GENERATED_SOURCE_HELPER = "generated.source.helper";
	
    /**
     * <p>The path to 'daogen-config.xml'</p>
     * 
     * <p>If it is a file, it is recommended to set it to the full path, for instance : 
     * <code>file://${project.basedir}/src/main/daogen/daogen-config.xml</code></p>
     * 
     * @since 1.1.0
     */
    @Parameter(property = "daogenConfig", required = true, alias = PARAM_DAOGEN_CONFIG )
    protected String daogenConfig;

    /**
     * <p>The generation source base directory, overrides <code>'base-src-folder'</code> daogen general property.</p>
     *  
     * <p>It is recommended to set it to the full path, for instance : <code>file://${project.basedir}</code></p>
     *  
     * @since 1.1.0
     */
    @Parameter(property = "genBaseDir", required = true, alias = DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER )
    protected String genBaseDir;

    /**
     * <p>Overrides <code>'src-mvn-generated-sources'</code> daogen general property.</p>
     * 
     * <p>It represents the generation source directory for maven generated sources 
     * (for instance 'target/generated-sources/daogen'),
     * relative to <code>'base-src-folder'</code></p>
     *  
     * @since 1.1.1
     */
    @Parameter(property = "generatedSourceHelper", required = false, alias = DaogenCatalogConstants.GEN_PROP_SRC_MVN_GENERATED )
    protected String generatedSourceHelper;
    
    /**
     * <p>Overrides <code>'generator-catalog'</code> daogen general property.</p>
     * 
     * <p>If it is a file, it is recommended to set it to the full path, for instance : 
     * <code>file://${project.basedir}/src/main/daogen/generator-catalog.xml</code></p>
     *  
     * @since 1.1.1
     */
    @Parameter(property = "generatorCatalog", required = false, alias = DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG )
    protected String generatorCatalog;
    
    /**
     * <p>Overrides <code>'decorator-catalog'</code> daogen general property.</p>
     * 
     * <p>If it is a file, it is recommended to set it to the full path, for instance : 
     * <code>file://${project.basedir}/src/main/daogen/decorator-catalog.xml</code></p>
     *  
     * @since 1.1.1
     */
    @Parameter(property = "decoratorCatalog", required = false, alias = DaogenCatalogConstants.GEN_PROP_DECORATOR_CATALOG )
    protected String decoratorCatalog;

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
        	 this.addProperty(overrideProperties, DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG, this.generatorCatalog);
        	 this.addProperty(overrideProperties, DaogenCatalogConstants.GEN_PROP_DECORATOR_CATALOG, this.decoratorCatalog);
             try ( FileInputStream fis = new FileInputStream( file ) ) {
             	DaogenFacade.generate( fis, overrideProperties );	
             }
        } catch (Exception e) {
        	throw new MojoExecutionException( "Error generating code : "+e, e );
        }
    }

}