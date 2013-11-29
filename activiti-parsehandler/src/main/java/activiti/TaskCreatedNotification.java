package activiti;

import java.util.Map;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Custom TaskListener. Whenever a user task is created, it broadcast a simple
 * event to be observed by business classes
 * 
 * @author felipereis
 * 
 */
@Named
public class TaskCreatedNotification implements TaskListener {
	private static final long serialVersionUID = 1L;

	@Inject
	private Event<TaskCreatedVO> event; // doesn't get injected

	@Inject
	private ProcessEngine processEngine; // doesn't get injected

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> vars = processEngine.getTaskService().getVariables(delegateTask.getId()); //NullPointerException at this line (processEngine is null)

		event.fire(new TaskCreatedVO(delegateTask.getAssignee(), delegateTask.getId(), vars)); //NullPointerException at this line (event is null)
	}
}