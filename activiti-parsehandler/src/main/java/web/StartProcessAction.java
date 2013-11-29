package web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

@ManagedBean
@RequestScoped
public class StartProcessAction {

	private static final ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@PostConstruct
	public void loadProcess() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("processes/test-process.bpmn").deploy();
	}

	public void startProcess() {
		ProcessDefinition pd = processEngine.getRepositoryService().createProcessDefinitionQuery().singleResult();

		processEngine.getRuntimeService().startProcessInstanceById(pd.getId());
	}
}
