/**
 * 
 */
package org.gpms.web;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author narenda.kumar
 * 
 */
public class TestActiviti implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("In side TestActiviti");
	}

}
