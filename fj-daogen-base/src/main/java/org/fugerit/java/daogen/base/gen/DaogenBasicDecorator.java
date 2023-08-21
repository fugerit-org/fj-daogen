package org.fugerit.java.daogen.base.gen;

public class DaogenBasicDecorator {

	private String generatorId;
	
	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}

	private DaogenBasicGenerator generator;
	
	protected DaogenBasicGenerator accessGenerator() {
		return generator;
	}

	public void init(DaogenBasicGenerator generator) {
		this.generator = generator;
		generator.getDecorators().add( this );
	}

	public void addImports() throws Exception {
		// do nothing implementation : sub classes should override it if needed
	}
	
	public void addAfterClassBody() throws Exception {
		// do nothing implementation : sub classes should override it if needed
	}
	
	public void addBeforeClassBody() throws Exception {
		// do nothing implementation : sub classes should override it if needed
	}

	public void addBeforeClass() throws Exception {
		// do nothing implementation : sub classes should override it if needed
	}

}
