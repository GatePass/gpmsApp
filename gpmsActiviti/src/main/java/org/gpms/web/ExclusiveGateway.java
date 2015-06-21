/**
 * 
 */
package org.gpms.web;

import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class ExclusiveGateway {

	public Boolean getDecision() {
		System.out.println("In Exclusive Gateway");
		return false;
	}

}
