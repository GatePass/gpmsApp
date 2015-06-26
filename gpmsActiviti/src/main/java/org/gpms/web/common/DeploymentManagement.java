/**
 * 
 */
package org.gpms.web.common;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class DeploymentManagement extends AbstractManagement {

	@Autowired
	public RuntimeService runtimeService;

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public IdentityService identityService;

	@Autowired
	public TaskService taskService;

	/**
	 * 
	 * @param classpath
	 * @param deploymentName
	 * @param deploymentCategory
	 * @return
	 */
	public Deployment createResourceDeployment(String classpath,
			String deploymentName, String deploymentCategory) {

		Deployment deployment = null;

		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment().addClasspathResource(classpath);
		deploymentBuilder.name(deploymentName);
		deploymentBuilder.category(deploymentCategory);
		deployment = deploymentBuilder.deploy();

		return deployment;

	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Deployment getDeploymentByName(String name) {

		DeploymentQuery deploymentQuery = repositoryService
				.createDeploymentQuery().deploymentName(name);
		Deployment deployment = deploymentQuery.singleResult();
		return deployment;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public ProcessDefinition getProcessDefByDeploymentName(String name) {

		Deployment deployment = getDeploymentByName(name);

		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery();
		ProcessDefinition processDefinition = processDefinitionQuery
				.deploymentId(deployment.getId()).singleResult();

		return processDefinition;

	}

}
