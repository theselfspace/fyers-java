package trade.theself.fyers.model.request;

/**
 * 
 * @author theselfspace
 *
 */
public class RequestOrder {

	private int side;
	private String productType;
	private int qty;
	private float limitPrice;
	private float stopPrice;
	private int type;
	private int disclosedQty;
	private String validity;
	private boolean offlineOrder;
	private String symbol;
	private float stopLoss;
	private float takeProfit;
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDisclosedQty() {
		return disclosedQty;
	}
	public void setDisclosedQty(int disclosedQty) {
		this.disclosedQty = disclosedQty;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public boolean isOfflineOrder() {
		return offlineOrder;
	}
	public void setOfflineOrder(boolean offlineOrder) {
		this.offlineOrder = offlineOrder;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getStopLoss() {
		return stopLoss;
	}
	public void setStopLoss(float stopLoss) {
		this.stopLoss = stopLoss;
	}
	public float getTakeProfit() {
		return takeProfit;
	}
	public void setTakeProfit(float takeProfit) {
		this.takeProfit = takeProfit;
	}
	
	
}
