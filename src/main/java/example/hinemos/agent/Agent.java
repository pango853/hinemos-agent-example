package example.hinemos.agent;

import com.clustercontrol.util.HinemosTime;
import com.clustercontrol.ws.agent.AgentInfo;

public class Agent {
	//private static final Log LOG = LogFactory.getLog(Agent.class);

	private static final int TOPIC_INTERVAL = 15_000;

	private String id;

	public Agent(String id) {
		this.id = id;
	}

	public AgentInfo getAgentInfo() {
		AgentInfo info = new AgentInfo();

		// 起動時刻
		info.setStartupTime(HinemosTime.currentTimeMillis());

		// TODO Replace with real values
		info.setFacilityId(id);
		info.setInstanceId(id);
		info.setHostname(id);

		info.setInterval(TOPIC_INTERVAL);

		return info;
	}
}
