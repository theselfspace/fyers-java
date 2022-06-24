package trade.theself.fyers.model.request;

/**
 * 
 * @author theselfspace
 *
 */
public class UpdateOrder {

	private String id;
	private int type;
	private float limitPrice;
	private float stopPrice;
	private int qty;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}
	public float getStopPrice() {
		return stopPrice;
	}
	public void setStopPrice(float stopPrice) {
		this.stopPrice = stopPrice;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
