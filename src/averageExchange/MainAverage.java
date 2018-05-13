package averageExchange;

import java.io.StringReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("exchangerates/rates")
public class MainAverage {
	
	public String getAverage(String tableChar, String currencyCode, Integer topCount){
		
		NBPcaller caller = new NBPcaller();
		String XMLdata = caller.callForXML(tableChar, currencyCode, topCount);
		try {
			JAXBContext jc = JAXBContext.newInstance(CurrencyInputData.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			StringReader reader = new StringReader(XMLdata);
			CurrencyInputData currencyInputData = (CurrencyInputData) unmarshaller.unmarshal(reader);
			return String.valueOf(currencyInputData.getAvgAskMid());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{tableChar}/{currencyCode}/{topCount}")
	public String getText(@PathParam("tableChar") String tableChar, @PathParam("currencyCode") String currencyCode, @PathParam("topCount") Integer topCount){
		return getAverage(tableChar,currencyCode,topCount);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{tableChar}/{currencyCode}/{topCount}")
	public String getHtml(@PathParam("tableChar") String tableChar, @PathParam("currencyCode") String currencyCode, @PathParam("topCount") Integer topCount) {
		return "<html><body><h1>Average rate</h1><p>Average rate for "+currencyCode+" calculated for the last "+topCount+" days is equal to: "+getAverage(tableChar,currencyCode,topCount)+" PLN</p></body></html>";
	}

	@GET
	@Path("{tableChar}/{currencyCode}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getXML(@PathParam("tableChar") String tableChar, @PathParam("currencyCode") String currencyCode, @PathParam("topCount") Integer topCount) {
		return "<?xml version=\"1.0\"?>" + "<avg>"+getAverage(tableChar,currencyCode,topCount)+"</avg>";
	}
	
	@GET
	@Path("{tableChar}/{currencyCode}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSON(@PathParam("tableChar") String tableChar, @PathParam("currencyCode") String currencyCode, @PathParam("topCount") Integer topCount) {
		return "{\"average\":" + getAverage(tableChar,currencyCode,topCount)+"}";
	}
}