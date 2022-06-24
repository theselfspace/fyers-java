package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class MarketStatus {

	private int exchange;
	private int segment;
	private String market_type;
	private String status;
	
	public int getExchange() {
		return exchange;
	}
	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	public int getSegment() {
		return segment;
	}
	public void setSegment(int segment) {
		this.segment = segment;
	}
	public String getMarket_type() {
		return market_type;
	}
	public void setMarket_type(String market_type) {
		this.market_type = market_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
