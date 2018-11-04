package example.hinemos.agent;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.BindingProvider;

import com.clustercontrol.ws.agent.AgentEndpoint;
import com.clustercontrol.ws.agent.AgentEndpointService;

public class Manager {
	private static final int TIMEOUT = 30_000;

	private URL url;
	private String user;
	private String password;

	public Manager(String url, String user, String password) throws MalformedURLException {
		this.url = new URL(url);
		this.user = user;
		this.password = password;
	}

	private static void setBindingProvider(Object o, String user, String password, String urlStr) {
		BindingProvider bp = (BindingProvider)o;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlStr);
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
		// We don't want to use proprietary Sun code
		// bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, 30_000);
		// bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 30_000);
		bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", TIMEOUT);
		bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", TIMEOUT);
	}

	public AgentEndpoint getAgentEndpoint() throws MalformedURLException {
		AgentEndpointService service = new AgentEndpointService(this.url);
		AgentEndpoint endpoint = service.getAgentEndpointPort();
		Manager.setBindingProvider(endpoint, user, password, this.user);

		return endpoint;
	}
}
