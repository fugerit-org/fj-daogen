package org.fugerit.java.daogen.sample.def.model;

import org.fugerit.java.daogen.sample.impl.helper.HelperAddress;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ModelAddress, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@org.fugerit.java.core.lang.annotate.DefineImpl(as = HelperAddress.class)
public interface ModelAddress extends org.fugerit.java.core.lang.compare.CheckEmpty {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/**
	 * Getter method for property : User (nullable: yes)
	 *
	 * relation to entity : USER
	 *
	 * @return the value of User
	 */
	ModelUser getUser();

	/**
	 * Setter method for property : User (nullable: yes)
	 *
	 * relation to entity : USER
	 *
	 * @param value the value of User
	 */
	void setUser( ModelUser value );

	/**
	 * Getter method for property : id (nullable: no)
	 *
	 * Address system id
	 *
	 * @return the value of id
	 */
	java.math.BigDecimal getId();

	/**
	 * Setter method for property : id (nullable: no)
	 *
	 * Address system id
	 *
	 * @param value the value of id
	 */
	void setId( java.math.BigDecimal value );

	/**
	 * Getter method for property : idUser (nullable: no)
	 *
	 * User linked to to address
	 *
	 * @return the value of idUser
	 */
	java.math.BigDecimal getIdUser();

	/**
	 * Setter method for property : idUser (nullable: no)
	 *
	 * User linked to to address
	 *
	 * @param value the value of idUser
	 */
	void setIdUser( java.math.BigDecimal value );

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
	 * Getter method for property : info (nullable: no)
	 *
	 * Address info
	 *
	 * @return the value of info
	 */
	java.lang.String getInfo();

	/**
	 * Setter method for property : info (nullable: no)
	 *
	 * Address info
	 *
	 * @param value the value of info
	 */
	void setInfo( java.lang.String value );

}
