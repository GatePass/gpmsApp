/**
 * 
 */
package org.gpms.web.common;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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

	public Task getTaskByProcessInstance(String processInstanceId) {

		Task task = taskService.createTaskQuery()
				.processInstanceId(processInstanceId).singleResult();

		return task;

	}

	public void completeTaskByProcessInstanceId(String processInstanceId) {
		Task task = getTaskByProcessInstance(processInstanceId);

		taskService.complete(task.getId());

	}

}
