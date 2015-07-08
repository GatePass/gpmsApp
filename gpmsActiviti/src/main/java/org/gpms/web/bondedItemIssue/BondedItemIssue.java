/**
 * 
 */
package org.gpms.web.bondedItemIssue;

import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
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

		System.out.println("processInstanceId : " + processInstanceId);

		return processInstanceId;

	}

	public void updateInfoOnTask(String processInstanceId, String comment,
			String variableName, Object objectValue) {
		taskManagement.updateInfoOnTask(processInstanceId, comment,
				variableName, objectValue);
	}

	public Object getVariableByTaskId(String taskId, String variableName) {
		Object varObject = taskManagement.getVariableByTaskId(taskId,
				variableName);
		return varObject;
	}

	public List<Comment> getCommentsByTaskId(String taskId) {
		List<Comment> commentsList = taskManagement.getCommentsByTaskId(taskId);
		return commentsList;
	}

	public void performApprovalAssignment(String processId, String groupId,
			String userId) {

		Execution execution = processManagement
				.getExecutionFromProcessId(processId);

		Task task = taskManagement.getTaskByExecutionId(execution.getId());

		taskManagement.assignTaskToUser(task.getId(), userId);
		taskManagement.assignTaskToGroup(task.getId(), groupId);

	}

	public List<Task> getAllTasksForAction(String userId) {
		List<Task> taskList = taskManagement.getAllTasksByAssignee(userId);
		return taskList;
	}

	public void completeTask(String processInstanceId) {
		taskManagement.completeTaskByProcessInstanceId(processInstanceId);
	}

	public void setVariableOnExecution(String processInstanceId,
			String variableName, Object variable) {
		processManagement.setVariableOnExecution(processInstanceId,
				variableName, variable);
	}

	public void setVariableOnTask(String processInstanceId,
			String variableName, Object variable) {
		taskManagement.setVariableOnTask(processInstanceId, variableName,
				variable);
	}

	public Task getTaskByProcessInstance(String processInstanceId) {
		Task task = taskManagement.getTaskByProcessInstance(processInstanceId);
		return task;
	}

}
