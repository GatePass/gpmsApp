/**
 * 
 */
package org.gpms.web.bondedItemIssue;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.gpms.web.common.BondedItemManagement;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class BondedItemIssue extends BondedItemManagement {

	String processInstanceId;

	private static String BONDED_ITEM_ISSUE_WORKFLOW = "workflows/BondedItemIssue.bpmn";

	private static String DEPLOYMENT_NAME = "Bonded Item Issue";

	private static String DEPLOYMENT_CATEGORY = "GPMS Process category";

	/**
	 * 
	 */
	public String startBondItemIssueProcess() {

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

		return processInstanceId;

	}

}
