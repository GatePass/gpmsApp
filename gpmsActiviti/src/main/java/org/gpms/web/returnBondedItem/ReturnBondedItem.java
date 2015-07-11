/**
 * 
 */
package org.gpms.web.returnBondedItem;

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
public class ReturnBondedItem extends BondedItemManagement {

	String processInstanceId;

	private static String BONDED_ITEM_ISSUE_WORKFLOW = "workflows/ReturnBondedItem.bpmn";

	private static String DEPLOYMENT_NAME = "Return Bonded Item";

	private static String DEPLOYMENT_CATEGORY = "GPMS Process category";

	/**
	 * 
	 */
	public String startReturnBondedItemProcess() {

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
