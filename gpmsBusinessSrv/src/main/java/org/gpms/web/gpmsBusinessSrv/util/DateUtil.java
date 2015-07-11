/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author narenda.kumar
 * 
 */
public class DateUtil {

	static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/YYYY hh:mm:ss");

	static SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/YYYY");

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

	public static Date getSQLDateForAsset(String dateToConvert) {

		java.sql.Date dateDB = null;

		try {
			java.util.Date dateStr = formatter2.parse(dateToConvert);
			dateDB = new java.sql.Date(dateStr.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return dateDB;
	}

}
