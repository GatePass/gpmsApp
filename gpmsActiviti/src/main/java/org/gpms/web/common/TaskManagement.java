/**
 * 
 */
package org.gpms.web.common;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class TaskManagement {

	@Autowired(required = true)
	public RuntimeService runtimeService;

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public IdentityService identityService;

	@Autowired
	public TaskService taskService;

	/**
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public Task getTaskByProcessInstance(String processInstanceId) {
		Task task = taskService.createTaskQuery()
				.processInstanceId(processInstanceId).singleResult();
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
		Task task = taskService.createTaskQuery()
				.taskDefinitionKey(taskDefinitionKey)
				.processInstanceId(processInstanceId).singleResult();
		return task;
	}

	/**
	 * 
	 * @param executionId
	 * @return
	 */
	public Task getTaskByExecutionId(String executionId) {
		Task task = taskService.createTaskQuery().executionId(executionId)
				.singleResult();
		return task;
	}

	/**
	 * 
	 * @param assignee
	 * @return
	 */
	public List<Task> getAllTasksByAssignee(String assignee) {
		List<Task> taskList = taskService.createTaskQuery()
				.taskAssignee(assignee).list();
		return taskList;
	}

	/**
	 * 
	 * @param processInstanceId
	 * @param comment
	 * @param variableName
	 * @param objectValue
	 */
	public void updateInfoOnTask(String processInstanceId, String comment,
			String variableName, Object objectValue) {

		Task task = getTaskByProcessInstance(processInstanceId);

		if (comment != null) {
			taskService.addComment(task.getId(), processInstanceId, comment);
		}
		if (variableName != null) {
			taskService.setVariable(task.getId(), variableName, objectValue);
		}

	}

	/**
	 * 
	 * @param taskId
	 * @return
	 */
	public List<Comment> getCommentsByTaskId(String taskId) {
		List<Comment> commentsList = taskService.getTaskComments(taskId);
		return commentsList;
	}

	/**
	 * 
	 * @param taskId
	 * @param variableName
	 * @return
	 */
	public Object getVariableByTaskId(String taskId, String variableName) {
		Object varObject = taskService.getVariable(taskId, variableName);
		return varObject;
	}

	/**
	 * 
	 * @param taskId
	 * @param userId
	 */
	public void assignTaskToUser(String taskId, String userId) {
		taskService.setOwner(taskId, userId);
		taskService.setAssignee(taskId, userId);
		taskService.addCandidateUser(taskId, userId);
	}

	/**
	 * 
	 * @param taskId
	 * @param groupId
	 */
	public void assignTaskToGroup(String taskId, String groupId) {
		taskService.addCandidateGroup(taskId, groupId);
	}

	/**
	 * 
	 * @param processInstanceId
	 */
	public void completeTaskByProcessInstanceId(String processInstanceId) {
		Task task = getTaskByProcessInstance(processInstanceId);
		taskService.complete(task.getId());
	}

	/**
	 * 
	 * @param processInstanceId
	 * @param variableName
	 * @param variable
	 */
	public void setVariableOnTask(String processInstanceId,
			String variableName, Object variable) {
		Task task = getTaskByProcessInstance(processInstanceId);
		taskService.setVariable(task.getId(), variableName, variable);

	}

}
