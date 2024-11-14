package org.fugerit.java.daogen.sample.def.model;

import org.fugerit.java.daogen.sample.impl.helper.HelperLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ModelLogData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@org.fugerit.java.core.lang.annotate.DefineImpl(as = HelperLogData.class)
public interface ModelLogData extends org.fugerit.java.core.lang.compare.CheckEmpty {

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
	 * Getter method for property : logTime (nullable: yes)
	 *
	 * 
	 *
	 * @return the value of logTime
	 */
	java.time.LocalDateTime getLogTime();

	/**
	 * Setter method for property : logTime (nullable: yes)
	 *
	 * 
	 *
	 * @param value the value of logTime
	 */
	void setLogTime( java.time.LocalDateTime value );

	/**
	 * Getter method for property : info (nullable: no)
	 *
	 * 
	 *
	 * @return the value of info
	 */
	java.lang.String getInfo();

	/**
	 * Setter method for property : info (nullable: no)
	 *
	 * 
	 *
	 * @param value the value of info
	 */
	void setInfo( java.lang.String value );

}
