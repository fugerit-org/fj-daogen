package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;

public abstract class DaogenBasicHelperGenerator extends DaogenBasicGenerator {

	public static final int MODE_FULL = 1;
	public static final int MODE_REAL = 2;
	public static final int MODE_HELPER = 3;

	protected DaogenBasicHelperGenerator() {
		this.mode = MODE_FULL;
	}
	
	@Override
	public void init( String sourceFolder, String fullObjectBName, String javaStyle, DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init(sourceFolder, fullObjectBName, javaStyle, daogenConfig, entity);
		if ( this.getMode() == MODE_REAL ) {
			this.setNoCustomComment( true );
		}
	}
	
	private int mode;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean isModeFull() {
		return this.getMode() == MODE_FULL;
	}
	
	public boolean isModeReal() {
		return this.getMode() == MODE_REAL;
	}
	
	public boolean isModeHelper() {
		return this.getMode() == MODE_HELPER;
	}
	
	public boolean isModeFullOrReal() {
		return this.isModeFull() || this.isModeReal();
	}
	
	public boolean isModeFullOrHelper() {
		return this.isModeFull() || this.isModeHelper();
	}
	
	protected void configRealClass() throws ConfigException {
		this.setExtendsClass( DaogenHelperGenerator.toHelperClassName( this.getJavaName() ) );
		try {
			this.checkSkipRealClass();
		} catch (IOException e) {
			throw new ConfigException( e );
		}
	}
	
	protected void generateRealClass() throws IOException {
		this.println( REAL_CLASS_COMMENT );
	}
	
}
