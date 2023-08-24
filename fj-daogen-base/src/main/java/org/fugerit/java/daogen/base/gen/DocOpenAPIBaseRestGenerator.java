package org.fugerit.java.daogen.base.gen;

import java.io.File;
import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;

public class DocOpenAPIBaseRestGenerator extends DaogenBasicGenerator {

	public static final int VERSION_V2 = 2; // swagger specification

	public static final int VERSION_V3 = 3; // openapi specification v3

	public static final int VERSION_DEFAULT = VERSION_V3;

	private static final String TYPE_LIT = "type: string";

	private static final String PROPERTIES_LIT = "properties:";
	
	private static final String SCHEMA_LIT = "schema:";
	
	public DocOpenAPIBaseRestGenerator(String key, int compatibility) {
		super();
		this.key = key;
		this.compatibility = compatibility;
	}

	private String key;

	private int compatibility;

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public void init(DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity) throws ConfigException {
		super.init(daogenConfig.getGeneralProp(DaogenCatalogConstants.GEN_PROP_SRC_DOC_OPENAPI),
				fullObjectName("", entity.getId()), STYLE_CLASS, daogenConfig, entity);
		File file = this.getJavaFile();
		file = new File(file.getParentFile(), file.getName().replace("java", "yaml"));
		this.setJavaFile(file);
	}

	@Override
	public void generateDaogenBody() throws IOException {
		// donothing()
	}

	private static String prepareText(String s) {
		String r = "";
		if (s != null) {
			r = s.replace('\'', ' ');
		}
		return r;
	}

	@Override
	public void generate() throws IOException {
		String urlBase = "/" + this.getCurrentEntity().getName().replace("_", "").toLowerCase() + "/load";
		String description = "Comments : " + prepareText(this.getCurrentEntity().getComments());
		String title = "OpenAPI load specification for " + prepareText(this.getCurrentEntity().getId());
		String version = prepareText(this.getDaogenConfig().getGeneralProp("gen-version"));
		String openapiHost = prepareText(this.getDaogenConfig().getGeneralProp("openapi_host"));
		String openapiPath = prepareText(this.getDaogenConfig().getGeneralProp("openapi_path")) + urlBase;
		String infoKey = "openapi.swagger.info.v3";
		if (this.compatibility == VERSION_V2) {
			infoKey = "openapi.swagger.info";
		}
		DaogenCustomCode.addDocGenOpenAPI(infoKey, "", this.getWriter(), description, title, version, openapiHost,
				openapiPath);
		String baseType = GeneratorNameHelper.toClassName(this.getCurrentEntity().getName());
		// primary key
		if (StringUtils.isNotEmpty(this.getCurrentEntity().getPrimaryKey())) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper(this.getDaogenConfig(),
					this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey()).setForLoadInterface();
			this.printlnWithTabs(1, primaryKeyHelper.getUrlParams() + ":");
			this.printlnWithTabs(2, "get:");
			this.printlnWithTabs(3, "summary: Get " + this.getCurrentEntity().getId() + " by primary key");
			this.printlnWithTabs(3, "parameters:");
			for (String currentField : primaryKeyHelper.getKeyFields()) {
				addPathParam(4, currentField);
			}
			this.addBasicResponse(3);
			if (this.compatibility == VERSION_V2) {
				this.printObjectWithBasicIndent(6);
			} else {
				this.printlnWithTabs(8, "$ref: '#/components/schemas/" + baseType + "'");
			}
		}
		// load all
		this.printlnWithTabs(1, "/all:");
		this.printlnWithTabs(2, "get:");
		this.printlnWithTabs(3, "summary: Get all " + this.getCurrentEntity().getId());
		this.addBasicResponse(3);
		if (this.compatibility == VERSION_V2) {
			this.printlnWithTabs(7, "type: array");
			this.printlnWithTabs(7, "items: ");
			this.printObjectWithBasicIndent(8);
		} else {
			this.printlnWithTabs(9, "type: array");
			this.printlnWithTabs(9, "items: ");
			this.printlnWithTabs(10, "$ref: '#/components/schemas/" + baseType + "'");
			this.printlnWithTabs(0, "components: ");
			this.printlnWithTabs(1, "schemas: ");
			this.printlnWithTabs(2, baseType + ": ");
			this.printObjectWithBasicIndent(3);
		}
	}

	private void addPathParam(int indent, String currentField) {
		if (this.compatibility == VERSION_V2) {
			this.printlnWithTabs(indent, "- in: path");
			this.printlnWithTabs(indent + 1, "name: " + currentField.toLowerCase());
			this.printlnWithTabs(indent + 1, "required: true");
			this.printlnWithTabs(indent + 1, TYPE_LIT);
			this.printlnWithTabs(indent + 1, "description: Value of field " + currentField);
		} else {
			this.printlnWithTabs(indent, "- name: " + currentField.toLowerCase());
			this.printlnWithTabs(indent + 1, "in: path");
			this.printlnWithTabs(indent + 1, "required: true");
			this.printlnWithTabs(indent + 1, "description: Value of field " + currentField);
			this.printlnWithTabs(indent + 1, SCHEMA_LIT);
			this.printlnWithTabs(indent + 2, TYPE_LIT);
		}
	}

	private void addBasicResponse(int indent) {
		this.printlnWithTabs(indent, "responses:");
		this.printlnWithTabs(indent + 1, "'200':");
		this.printlnWithTabs(indent + 2, "description: Success response");
		if (this.compatibility == VERSION_V2) {
			this.printlnWithTabs(indent + 2, SCHEMA_LIT);
		} else {
			this.printlnWithTabs(indent + 2, "content:");
			this.printlnWithTabs(indent + 3, "application/json:");
			this.printlnWithTabs(indent + 4, SCHEMA_LIT);
		}
	}

	private void printObjectWithBasicIndent(int indent) {
		if (this.compatibility == VERSION_V2) {
			this.printlnWithTabs(indent, "type: object");
			this.printlnWithTabs(indent, PROPERTIES_LIT);
			for (DaogenCatalogField field : this.getCurrentEntity()) {
				this.printlnWithTabs(indent + 1, field.getId().toLowerCase() + ":");
				this.printlnWithTabs(indent + 2, TYPE_LIT);
				this.printlnWithTabs(indent + 2, "example: ''");
			}
		} else {
			this.printlnWithTabs(indent, PROPERTIES_LIT);
			for (DaogenCatalogField field : this.getCurrentEntity()) {
				this.printlnWithTabs(indent + 1, GeneratorNameHelper.toPropertyName(field.getId().toLowerCase()) + ":");
				this.printlnWithTabs(indent + 2, TYPE_LIT);
				this.printlnWithTabs(indent + 2, "example: ''");
			}
		}
	}

}
