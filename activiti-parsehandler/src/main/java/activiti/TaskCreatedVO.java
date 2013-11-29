package activiti;

import java.util.Map;

/**
 * POJO to broadcast task creation event 
 * 
 * @author felipereis
 *
 */
public class TaskCreatedVO {
	private final String assignee;
	private final String id;
	private final Map<String, Object> variables;
	
	public TaskCreatedVO(String assignee, String id, Map<String, Object> variables) {
		this.assignee = assignee;
		this.id = id;
		this.variables = variables;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getId() {
		return id;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}
}
