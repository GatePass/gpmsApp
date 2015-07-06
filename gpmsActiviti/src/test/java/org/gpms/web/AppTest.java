package org.gpms.web;

import java.util.Iterator;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.gpms.web.common.TaskManagement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@ContextConfiguration("classpath*:activiti-app-context.xml")
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

	@Autowired
	private IdentityService identityService;

	@Autowired
	private TaskService taskService;

	@Autowired
	TaskManagement taskManagement;

	@Test
	public void testTask() {

		// Task task = taskService.createTaskQuery().processInstanceId("32502")
		// .singleResult();
		//
		// System.out.println(task.getId());
		// taskService.complete(task.getId());

		// repositoryService.deleteDeploymentCascade("65002");

		List<Task> taskList = taskManagement
				.getAllTasksByAssignee("gpmsISITManager@gmail.com");

		Iterator<Task> taskListIter = taskList.iterator();

		Task task = null;
		while (taskListIter.hasNext()) {
			task = taskListIter.next();
			System.out.println("task  : " + task.getId());
		}

		if (task != null) {
			String taskId = task.getId();

			taskService.setVariable(taskId, "isApproved", true);
			taskService.complete(taskId);
		}

	}

	/**
	 * 
	 */
	// @Test
	public void testProcessCreation() {

		Group group = identityService.newGroup("gpmsISITMgrRole");
		group.setName("ISITManager");
		group.setType("assignment");
		identityService.saveGroup(group);

		User user = identityService.newUser("narenda.kumar@cgi.com");
		user.setPassword("narenda.kumar");
		identityService.saveUser(user);

		identityService.createMembership(user.getId(), group.getId());

		System.out.println("identityService.createGroupQuery().count()"
				+ identityService.createGroupQuery().count());

		List<Group> groupList = identityService.createGroupQuery().list();

		System.out.println("groupList count " + groupList.size());

		DeploymentQuery deploymentQuery = repositoryService
				.createDeploymentQuery().deploymentName("Bonded Item Issue");
		Deployment deploymentFound = deploymentQuery.singleResult();

		Deployment deployment = null;

		// if (deploymentFound == null) {

		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment().addClasspathResource(
						"workflows/BondedItemIssue.bpmn");
		deploymentBuilder.name("Bonded Item Issue");
		deploymentBuilder.category("GPMS Process category");
		deployment = deploymentBuilder.deploy();
		deploymentFound = deployment;
		// } else {
		// System.out.println("deploymentFound : " + deploymentFound.getId());
		// }

		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery();
		ProcessDefinition processDefinition = processDefinitionQuery
				.deploymentId(deploymentFound.getId()).singleResult();

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId());
		runtimeService.addParticipantGroup(
				processInstance.getProcessInstanceId(), group.getId());
		runtimeService.addParticipantUser(
				processInstance.getProcessInstanceId(), user.getId());

		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId(processInstance.getProcessInstanceId())
				.singleResult();
		System.out.println(execution.getId());

		Task task = taskService.createTaskQuery()
				.executionId(execution.getId()).singleResult();
		taskService.complete(task.getId());
		// runtimeService.taskService.createTaskQuery().

		identityService.deleteMembership(user.getId(), group.getId());
		identityService.deleteUser(user.getId());
		identityService.deleteGroup(group.getId());

		System.out.println("identityService.createGroupQuery().count()"
				+ identityService.createGroupQuery().count());

		groupList = identityService.createGroupQuery().list();

		System.out.println("groupList count " + groupList.size());

		// repositoryService.deleteDeploymentCascade(deployment.getId());

	}
}
