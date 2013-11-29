package activiti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.parse.BpmnParseHandler;

/**
 * Custom Parse Handler. Adds custom listener to all user tasks
 * @author felipereis
 *
 */
public class ParseHandlerCreateTaskListener implements BpmnParseHandler {

	@Inject
	private TaskCreatedNotification listener; // the listener doesn't get injected

	@Override
	public Collection<Class<? extends BaseElement>> getHandledTypes() {
		List<Class<? extends BaseElement>> elementos = new ArrayList<>();

		elementos.add(UserTask.class);

		return elementos;
	}

	@Override
	public void parse(BpmnParse bpmnParse, BaseElement element) {
		String taskDefinitionKey = element.getId();

		TaskDefinition taskDefinition = ((ProcessDefinitionEntity) bpmnParse.getCurrentScope().getProcessDefinition()).getTaskDefinitions().get(
				taskDefinitionKey);

		if (taskDefinition != null) {
			/* 
			 * Add injected listener or instantiated listener. Neither work. 
			 * Injected listener is always null
			 * Instantiated listener does not have its dependencies injected. Check the TaskCreatedNotification class for detailed comments
			 *   
			 */

			taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, listener);
			//			taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, new TaskCreatedNotification());
		}
	}
}
