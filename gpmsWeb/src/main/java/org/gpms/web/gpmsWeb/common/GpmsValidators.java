/**
 * 
 */
package org.gpms.web.gpmsWeb.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author narenda.kumar
 * 
 */
public class GpmsValidators {

	/**
	 * 
	 * @param date
	 * @param beanName
	 * @param beanField
	 * @param result
	 * @return
	 */
	public static BindingResult validateDateField(String date, String beanName,
			String beanField, BindingResult result) {

		if (date != null) {
			if (!"".equals(date)) {
				DateValidator dateValidator = new DateValidator();
				boolean isDateValid = dateValidator.validate(date);

				if (!isDateValid) {
					FieldError dateFldErr = new FieldError(beanName, beanField,
							"Please enter the date in valid date format (dd/MM/yyyy).");
					result.addError(dateFldErr);
				}
			} else {
				FieldError dateFldErr = new FieldError(beanName, beanField,
						"Please enter the issue date. It cannot be blank");
				result.addError(dateFldErr);
			}
		}

		return result;
	}

	/**
	 * 
	 * @param fieldValue
	 * @param beanName
	 * @param beanField
	 * @param result
	 * @return
	 */
	public static BindingResult validateBlankField(String fieldValue,
			String beanName, String beanField, BindingResult result) {

		if (fieldValue != null && "".equals(fieldValue)) {
			FieldError fldBlankErr = new FieldError(beanName, beanField,
					"Please enter the field value. It cannot be blank");
			result.addError(fldBlankErr);
		}

		return result;
	}

}
