package org.gpms.web;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.activiti.engine.ProcessEngineConfiguration;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */

	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		System.out.println("((((((((" + pec.getDataSourceJndiName());
		System.out.println(pec.getJdbcDriver());
		System.out.println(pec.getJdbcUrl());
		System.out.println(pec.getJdbcUsername());
		System.out.println(pec.getJdbcPassword());
		assertTrue(true);
	}
}
