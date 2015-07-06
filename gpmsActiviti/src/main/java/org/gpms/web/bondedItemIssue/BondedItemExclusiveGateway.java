package org.gpms.web.bondedItemIssue;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

@Component
public class BondedItemExclusiveGateway implements JavaDelegate {

	Boolean bondedItemApproved;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String processInstanceId = execution.getProcessInstanceId();

		Task task = execution.getEngineServices().getTaskService()
				.createTaskQuery().processInstanceId(processInstanceId)
				.singleResult();

		Boolean taskVariable = (Boolean) execution.getEngineServices()
				.getTaskService().getVariable(task.getId(), "isApproved");

		bondedItemApproved = Boolean.valueOf(taskVariable);

		execution.setVariable("bondedItemApproved", bondedItemApproved);
	}

	/**
	 * @return the bondedItemApproved
	 */
	public Boolean getBondedItemApproved() {
		return bondedItemApproved;
	}

	/**
	 * @param bondedItemApproved
	 *            the bondedItemApproved to set
	 */
	public void setBondedItemApproved(Boolean bondedItemApproved) {
		this.bondedItemApproved = bondedItemApproved;
	}

}
