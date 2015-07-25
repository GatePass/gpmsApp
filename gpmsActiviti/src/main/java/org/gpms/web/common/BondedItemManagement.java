/**
 * 
 */
package org.gpms.web.common;

import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class BondedItemManagement {

	@Autowired
	protected DeploymentManagement deploymentManagement;

	@Autowired
	protected ProcessManagement processManagement;

	@Autowired
	protected TaskManagement taskManagement;

	@Autowired
	protected IdentityManagement identityManagement;

	/**
	 * 
	 * @param processInstanceId
	 * @param comment
	 * @param variableName
	 * @param objectValue
	 */
	public void updateInfoOnTask(String processInstanceId, String comment,
			String variableName, Object objectValue) {
		taskManagement.updateInfoOnTask(processInstanceId, comment,
				variableName, objectValue);
	}

	/**
	 * 
	 * @param taskId
	 * @param variableName
	 * @return
	 */
	public Object getVariableByTaskId(String taskId, String variableName) {
		Object varObject = taskManagement.getVariableByTaskId(taskId,
				variableName);
		return varObject;
	}

	/**
	 * 
	 * @param taskId
	 * @return
	 */
	public List<Comment> getCommentsByTaskId(String taskId) {
		List<Comment> commentsList = taskManagement.getCommentsByTaskId(taskId);
		return commentsList;
	}

	/**
	 * 
	 * @param taskDefinitionKey
	 * @param processId
	 * @param groupId
	 * @param userId
	 */
	public void performApprovalAssignment(String taskDefinitionKey,
			String processId, String groupId, String userId) {

		Task task = taskManagement.getTaskByProcessInstAndDefKey(
				taskDefinitionKey, processId);

		taskManagement.assignTaskToUser(task.getId(), userId);
		taskManagement.assignTaskToGroup(task.getId(), groupId);

	}

	/**
	 * 
	 * @param processId
	 * @param groupId
	 * @param userId
	 */
	public void performApprovalAssignment(String processId, String groupId,
			String userId) {

		Execution execution = processManagement
				.getExecutionFromProcessId(processId);

		Task task = taskManagement.getTaskByExecutionId(execution.getId());

		taskManagement.assignTaskToUser(task.getId(), userId);
		taskManagement.assignTaskToGroup(task.getId(), groupId);

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Task> getAllTasksForAction(String userId) {
		List<Task> taskList = taskManagement.getAllTasksByAssignee(userId);
		return taskList;
	}

	/**
	 * 
	 * @param processInstanceId
	 */
	public void completeTask(String processInstanceId) {
		taskManagement.completeTaskByProcessInstanceId(processInstanceId);
	}

	/**
	 * 
	 * @param processInstanceId
	 * @param variableName
	 * @param variable
	 */
	public void setVariableOnExecution(String processInstanceId,
			String variableName, Object variable) {
		processManagement.setVariableOnExecution(processInstanceId,
				variableName, variable);
	}

	/**
	 * 
	 * @param processInstanceId
	 * @param variableName
	 * @param variable
	 */
	public void setVariableOnTask(String processInstanceId,
			String variableName, Object variable) {
		taskManagement.setVariableOnTask(processInstanceId, variableName,
				variable);
	}

	/**
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public Task getTaskByProcessInstance(String processInstanceId) {
		Task task = taskManagement.getTaskByProcessInstance(processInstanceId);
		return task;
	}

	/**
	 * 
	 * @param taskDefinitionKey
	 * @param processInstanceId
	 * @return
	 */
	public Task getTaskByProcessInstAndDefKey(String taskDefinitionKey,
			String processInstanceId) {
		Task task = taskManagement.getTaskByProcessInstAndDefKey(
				taskDefinitionKey, processInstanceId);
		return task;
	}

	/**
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public String getProcessStarterByProcessId(String processInstanceId) {

		HistoricProcessInstance historicProcessInstance = processManagement
				.getHistoricProcessInstanceById(processInstanceId);
		return historicProcessInstance.getStartUserId();
	}

	/**
	 * 
	 * @param authenticatedUserId
	 */
	public void setUserForProcessStarter(String authenticatedUserId) {
		identityManagement.setUserForProcessStarter(authenticatedUserId);
	}

}
