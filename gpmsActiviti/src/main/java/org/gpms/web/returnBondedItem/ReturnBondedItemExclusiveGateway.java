package org.gpms.web.returnBondedItem;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

@Component
public class ReturnBondedItemExclusiveGateway implements JavaDelegate {

	Boolean bondedItemReturnApproved;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String processInstanceId = execution.getProcessInstanceId();

		Task task = execution.getEngineServices().getTaskService()
				.createTaskQuery().processInstanceId(processInstanceId)
				.singleResult();

		Boolean taskVariable = (Boolean) execution.getEngineServices()
				.getTaskService().getVariable(task.getId(), "isApproved");

		bondedItemReturnApproved = Boolean.valueOf(taskVariable);

		execution.setVariable("bondedItemReturnApproved",
				bondedItemReturnApproved);
	}

	/**
	 * @return the bondedItemApproved
	 */
	public Boolean getBondedItemReturnApproved() {
		return bondedItemReturnApproved;
	}

	/**
	 * @param bondedItemApproved
	 *            the bondedItemApproved to set
	 */
	public void setBondedItemReturnApproved(Boolean bondedItemReturnApproved) {
		this.bondedItemReturnApproved = bondedItemReturnApproved;
	}

}
