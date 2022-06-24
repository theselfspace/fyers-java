package trade.theself.fyers.model.request;

/**
 * 
 * @author theselfspace
 *
 */
public class EdisRecord {

	private String isin_code; 
	private int qty;
	
	public String getIsin_code() {
		return isin_code;
	}
	public void setIsin_code(String isin_code) {
		this.isin_code = isin_code;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
