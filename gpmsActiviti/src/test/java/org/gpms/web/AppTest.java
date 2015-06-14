package org.gpms.web;

import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@ContextConfiguration("classpath*:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest extends AbstractJUnit4SpringContextTests {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * @return the suite of tests being tested
	 */
	// public static Test suite() {
	// return new TestSuite(AppTest.class);
	// }

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() {

		System.out.println("runtimeService " + runtimeService);

		// assertTrue(true);
	}
}
