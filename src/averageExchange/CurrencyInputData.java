package averageExchange;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExchangeRatesSeries")
public class CurrencyInputData {

	@XmlElement(name="Table")
	private String Table;
	
	@XmlElement(name="Currency")
	private String Currency;
	
	@XmlElement(name="Code")
	private String Code;
	
	@XmlElementWrapper(name="Rates")
	@XmlElement(name="Rate")
	private List<Rate> Rate = new ArrayList<Rate>();
	
	public String getTable() {
		return Table;
	}

	public void setTable(String table) {
		Table = table;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}
	
	public List<Rate> getRate() {
		return Rate;
	}

	public void setRate(List<Rate> rate) {
		Rate = rate;
	}
	
	public Double getAvgAskMid() {
		Double sum = new Double(0);
		for(Rate r : Rate) {
			Double ask = r.getAsk(), mid = r.getMid();
			if(ask > 0) sum += ask;
			else if(mid > 0) sum += mid;
		}
		return (double)(Math.round((sum / Rate.size())*100))/100;
	}
	
	
}