/**
 * 
 */
package org.gpms.web.gpmsWeb.common;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.DSAPrivateKey;
import java.util.Enumeration;

/**
 * @author narenda.kumar
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Provider p[] = Security.getProviders();
			for (int i = 0; i < p.length; i++) {
				System.out.println(p[i]);
				for (Enumeration e = p[i].keys(); e.hasMoreElements();)
					System.out.println("\t" + e.nextElement());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			KeyPairGenerator keyPair = KeyPairGenerator.getInstance("DSA",
					"SUN");
			KeyPair kp = keyPair.genKeyPair();
			System.out.println("kp --- > \n" + kp.getPublic());
			System.out.println("kp --- > \n" + kp.getPrivate());
			DSAPrivateKey dpk = (DSAPrivateKey) kp.getPrivate();
			System.out.println("kp --- > \n" + dpk.getX());

		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}

	}

}
