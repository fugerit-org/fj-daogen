package org.fugerit.java.daogen.base.config;

import org.fugerit.java.core.cfg.xml.BasicIdConfigType;

public class DaogenCatalogRelation extends BasicIdConfigType {

	public static final String ATT_ID = "id";
	public static final String ATT_NAME = "name";
	public static final String ATT_FROM = "from";
	public static final String ATT_TO = "to";
	public static final String ATT_COMMENT = "comment";
	public static final String ATT_MODE = "mode";
	
	public static final String MODE_ONE = "one";
	public static final String MODE_MANY = "many";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 843790445976612481L;

	private String from;
	
	private String to;
	
	private String mode;
	
	private String name;
	
	private String comment;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}