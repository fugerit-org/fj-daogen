package org.fugerit.java.daogen.sample.def.model;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ModelUserData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface ModelUserData extends org.fugerit.java.core.lang.compare.CheckEmpty {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/**
	 * Getter method for property : id (nullable: no)
	 *
	 * User system id
	 *
	 * @return the value of id
	 */
	java.math.BigDecimal getId();

	/**
	 * Setter method for property : id (nullable: no)
	 *
	 * User system id
	 *
	 * @param value the value of id
	 */
	void setId( java.math.BigDecimal value );

	/**
	 * Getter method for property : username (nullable: no)
	 *
	 * User chosen id
	 *
	 * @return the value of username
	 */
	java.lang.String getUsername();

	/**
	 * Setter method for property : username (nullable: no)
	 *
	 * User chosen id
	 *
	 * @param value the value of username
	 */
	void setUsername( java.lang.String value );

	/**
	 * Getter method for property : password (nullable: no)
	 *
	 * Password hash
	 *
	 * @return the value of password
	 */
	java.lang.String getPassword();

	/**
	 * Setter method for property : password (nullable: no)
	 *
	 * Password hash
	 *
	 * @param value the value of password
	 */
	void setPassword( java.lang.String value );

	/**
	 * Getter method for property : lastLogin (nullable: yes)
	 *
	 * Time of last user login
	 *
	 * @return the value of lastLogin
	 */
	java.util.Date getLastLogin();

	/**
	 * Setter method for property : lastLogin (nullable: yes)
	 *
	 * Time of last user login
	 *
	 * @param value the value of lastLogin
	 */
	void setLastLogin( java.util.Date value );

	/**
	 * Getter method for property : dateInsert (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of dateInsert
	 */
	java.util.Date getDateInsert();

	/**
	 * Setter method for property : dateInsert (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of dateInsert
	 */
	void setDateInsert( java.util.Date value );

	/**
	 * Getter method for property : dateUpdate (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of dateUpdate
	 */
	java.util.Date getDateUpdate();

	/**
	 * Setter method for property : dateUpdate (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of dateUpdate
	 */
	void setDateUpdate( java.util.Date value );

	/**
	 * Getter method for property : state (nullable: no)
	 *
	 * 1 active, 0 not active
	 *
	 * @return the value of state
	 */
	java.math.BigDecimal getState();

	/**
	 * Setter method for property : state (nullable: no)
	 *
	 * 1 active, 0 not active
	 *
	 * @param value the value of state
	 */
	void setState( java.math.BigDecimal value );

}
