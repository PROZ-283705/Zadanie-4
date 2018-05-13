package averageExchange;

import javax.xml.bind.annotation.XmlElement;

public class Rate {
	@XmlElement(name="Ask")
	private Double Ask = 0.0;
	@XmlElement(name="Mid")
	private Double Mid = 0.0;
	
	public Double getAsk() {
		return Ask;
	}
	public void setAsk(Double ask) {
		Ask = ask;
	}
	public Double getMid() {
		return Mid;
	}
	public void setMid(Double mid) {
		Mid = mid;
	}
}
