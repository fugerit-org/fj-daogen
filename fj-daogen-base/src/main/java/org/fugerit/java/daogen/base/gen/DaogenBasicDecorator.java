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
		
	}
	
	public void addAfterClassBody() throws Exception {
		
	}
	
	public void addBeforeClassBody() throws Exception {
		
	}

	public void addBeforeClass() throws Exception {
		
	}

}
