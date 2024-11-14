package org.fugerit.java.daogen.sample.def.model;

import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ModelUpload, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@org.fugerit.java.core.lang.annotate.DefineImpl(as = HelperUpload.class)
public interface ModelUpload extends org.fugerit.java.core.lang.compare.CheckEmpty {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/**
	 * Getter method for property : id (nullable: no)
	 *
	 * 
	 *
	 * @return the value of id
	 */
	java.math.BigDecimal getId();

	/**
	 * Setter method for property : id (nullable: no)
	 *
	 * 
	 *
	 * @param value the value of id
	 */
	void setId( java.math.BigDecimal value );

	/**
	 * Getter method for property : dateInsert (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of dateInsert
	 */
	java.time.LocalDateTime getDateInsert();

	/**
	 * Setter method for property : dateInsert (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of dateInsert
	 */
	void setDateInsert( java.time.LocalDateTime value );

	/**
	 * Getter method for property : dateUpdate (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of dateUpdate
	 */
	java.time.LocalDateTime getDateUpdate();

	/**
	 * Setter method for property : dateUpdate (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of dateUpdate
	 */
	void setDateUpdate( java.time.LocalDateTime value );

	/**
	 * Getter method for property : content (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of content
	 */
	org.fugerit.java.core.db.daogen.ByteArrayDataHandler getContent();

	/**
	 * Setter method for property : content (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of content
	 */
	void setContent( org.fugerit.java.core.db.daogen.ByteArrayDataHandler value );

}
