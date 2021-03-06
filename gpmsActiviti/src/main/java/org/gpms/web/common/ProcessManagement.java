/**
 * 
 */
package org.gpms.web.common;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class ProcessManagement {

	@Autowired
	public RuntimeService runtimeService;

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public IdentityService identityService;

	@Autowired
	public TaskService taskService;

	@Autowired
	public HistoryService historyService;

	/**
	 * 
	 * @param processDefinition
	 * @return
	 */
	public ProcessInstance startProcessInstanceByProcessDef(
			ProcessDefinition processDefinition) {

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId());

		return processInstance;
	}

	/**
	 * 
	 * @param processId
	 * @return
	 */
	public Execution getExecutionFromProcessId(String processId) {

		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId(processId).singleResult();

		return execution;
	}

	/**
	 * 
	 * @param processInstanceId
	 * @param variableName
	 * @param variable
	 */
	public void setVariableOnExecution(String processInstanceId,
			String variableName, Object variable) {

		Execution execution = getExecutionFromProcessId(processInstanceId);

		runtimeService.setVariable(execution.getId(), variableName, variable);

	}

	/**
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public HistoricProcessInstance getHistoricProcessInstanceById(
			String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		return historicProcessInstance;
	}
}
