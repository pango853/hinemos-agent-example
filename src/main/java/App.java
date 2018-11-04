
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.clustercontrol.ws.agent.AgentEndpoint;
import com.clustercontrol.ws.agent.HinemosTopicInfo;
import com.clustercontrol.ws.agent.HinemosUnknown_Exception;
import com.clustercontrol.ws.agent.InvalidRole_Exception;
import com.clustercontrol.ws.agent.InvalidUserPass_Exception;
import com.clustercontrol.ws.agent.TopicInfo;

import example.hinemos.agent.Agent;
import example.hinemos.agent.Manager;

public class App {

	private static final Log LOG = LogFactory.getLog(App.class);

	public void getTopic(String url, String user, String password) throws MalformedURLException, HinemosUnknown_Exception, InvalidRole_Exception, InvalidUserPass_Exception {
		Agent agent = new Agent("TESTINSTANCE01");
		Manager manager = new Manager(url, user, password);

		AgentEndpoint endpoint = manager.getAgentEndpoint();
		HinemosTopicInfo topic = endpoint.getHinemosTopic(agent.getAgentInfo());

		List<TopicInfo> topicInfoList = topic.getTopicInfoList();
		//SettingUpdateInfo updateInfo = topic.getSettingUpdateInfo();

		LOG.info(String.format("Got %d topics.", topicInfoList.size()));
	}

	public static void main(String[] args) {
		String url = "127.0.0.1";
		String user = "hinemos";
		String password = "hinemos";

		App app = new App();
		try {
			app.getTopic(url, user, password);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HinemosUnknown_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRole_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserPass_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
