/**
 * 
 */
package org.gpms.web.bondedItemIssue;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.gpms.web.common.DeploymentManagement;
import org.gpms.web.common.ProcessManagement;
import org.gpms.web.common.TaskManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class BondedItemIssue {

	@Autowired
	DeploymentManagement deploymentManagement;

	@Autowired
	ProcessManagement processManagement;

	@Autowired
	TaskManagement taskManagement;

	String processInstanceId;

	private static String BONDED_ITEM_ISSUE_WORKFLOW = "workflows/BondedItemIssue.bpmn";

	private static String DEPLOYMENT_NAME = "Bonded Item Issue";

	private static String DEPLOYMENT_CATEGORY = "GPMS Process category";

	/**
	 * 
	 */
	public String startBondItemIssueProcess(String userAssetId) {

		Deployment deployment = deploymentManagement
				.getDeploymentByName(DEPLOYMENT_NAME);

		if (deployment == null) {
			deploymentManagement.createResourceDeployment(
					BONDED_ITEM_ISSUE_WORKFLOW, DEPLOYMENT_NAME,
					DEPLOYMENT_CATEGORY);
		}

		ProcessDefinition processDefinition = deploymentManagement
				.getProcessDefByDeploymentName(DEPLOYMENT_NAME);

		ProcessInstance processInstance = processManagement
				.startProcessInstanceByProcessDef(processDefinition);

		processInstanceId = processInstance.getId();

		System.out.println("processInstanceId : " + processInstanceId);

		return processInstanceId;

	}

	public void assignProcessToUserAndGroup(String processId, String userId,
			String groupId) {
		processManagement.assignProcessToUser(processId, userId);
		processManagement.assignProcessToGroup(processId, groupId);
	}

	public void completeTask(String processInstanceId) {

		taskManagement.completeTaskByProcessInstanceId(processInstanceId);

	}

}
