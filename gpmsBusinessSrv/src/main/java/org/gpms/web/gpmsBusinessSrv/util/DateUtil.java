/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author narenda.kumar
 * 
 */
public class DateUtil {

	private static final Logger logger = Logger.getLogger(DateUtil.class);

	static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss");

	static SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static Date getSQLDate(String dateToConvert) {

		if (logger.isDebugEnabled()) {
			logger.debug("dateToConvert : " + dateToConvert);
		}

		java.sql.Date dateDB = null;

		try {
			java.util.Date dateStr = formatter.parse(dateToConvert);
			dateDB = new java.sql.Date(dateStr.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("dateDB : " + dateDB);
		}

		return dateDB;
	}

	/**
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public static Date getSQLDateForTimeStamp(String dateToConvert) {

		if (logger.isDebugEnabled()) {
			logger.debug("dateToConvert : " + dateToConvert);
		}

		java.sql.Date dateDB = null;

		try {
			java.util.Date dateStr = formatter2.parse(dateToConvert);
			dateDB = new java.sql.Date(dateStr.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("dateDB : " + dateDB);
		}

		return dateDB;
	}

	/**
	 * 
	 * @param dateToFormat
	 * @return
	 */
	public static String getDateFormattedString(Date dateToFormat) {

		if (logger.isDebugEnabled()) {
			logger.debug("dateToFormat : " + dateToFormat);
		}

		String returnFormattedDate = null;

		returnFormattedDate = formatter2.format(dateToFormat);

		if (logger.isDebugEnabled()) {
			logger.debug("returnFormattedDate : " + returnFormattedDate);
		}

		return returnFormattedDate;

	}

}
