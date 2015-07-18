/**
 * 
 */
package org.gpms.web.gpmsWeb.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author narenda.kumar
 * 
 */
public class PasswordEncoderGenerator {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int i = 0;
		while (i < 10) {
			String password = "gpmsSecu777#$";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}

	}
}
