/**
 * 
 */
package org.gpms.web.common;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class ProcessManagement extends AbstractManagement {

	@Autowired
	public RuntimeService runtimeService;

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public IdentityService identityService;

	@Autowired
	public TaskService taskService;

	public ProcessInstance startProcessInstanceByProcessDef(
			ProcessDefinition processDefinition) {

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId());

		return processInstance;
	}

	public void assignProcessToGroup(String processInstanceId, String groupId) {

		runtimeService.addParticipantGroup(processInstanceId, groupId);

	}
}
