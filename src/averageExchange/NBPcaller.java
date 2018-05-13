package averageExchange;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class NBPcaller {
	
	private Client client;
	private URI uri;
	
	public NBPcaller(){
		client = ClientBuilder.newClient();
		uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates").build();
	}
	
	public String callForXML(String tableChar, String currencyCode, Integer topCount) {
		WebTarget webTarget = client.target(uri).path(tableChar).path(currencyCode).path("last").path(topCount.toString());
		return webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
	}
	
}
