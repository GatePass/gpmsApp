package org.gpms.web;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@ContextConfiguration("classpath*:activiti-test-context.xml")
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

	@Autowired
	private RepositoryService repositoryService;

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

		repositoryService.createDeployment()
				.addClasspathResource("workflows/BondedItemIssue.bpmn")
				.deploy();
		Map<String, Object> variableMap = new HashMap<String, Object>();

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("BondedItemIssue", variableMap);
		System.out.println("processInstance " + processInstance);

		// assertTrue(true);
	}
}
