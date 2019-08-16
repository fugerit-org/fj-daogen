package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.xml.BasicIdConfigType;
import org.fugerit.java.core.lang.helpers.StringUtils;

public class DaogenCatalogField extends BasicIdConfigType {

	public static final String ATT_ID = "id";
	public static final String ATT_COMMENTS = "comments";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7578925751213309863L;
	
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String describe() {
		 List<String> list = new ArrayList<String>();
		 list.add( StringUtils.concat( ":" , ATT_COMMENTS, this.getComments() ) );
		 return StringUtils.concat(  ",", list );
	}
	
}
