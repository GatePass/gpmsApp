/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author narenda.kumar
 * 
 */
public class DateUtil {

	static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss");

	static SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

	public static Date getSQLDate(String dateToConvert) {

		java.sql.Date dateDB = null;

		try {
			java.util.Date dateStr = formatter.parse(dateToConvert);
			dateDB = new java.sql.Date(dateStr.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return dateDB;
	}

	public static Date getSQLDateForTimeStamp(String dateToConvert) {

		java.sql.Date dateDB = null;

		try {
			java.util.Date dateStr = formatter2.parse(dateToConvert);
			dateDB = new java.sql.Date(dateStr.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return dateDB;
	}

	public static String getDateFormattedString(Date dateToFormat) {

		String returnFormattedDate = null;

		returnFormattedDate = formatter2.format(dateToFormat);

		return returnFormattedDate;

	}

}
