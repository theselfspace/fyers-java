package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class Holding {

	private String holdingType;
	private int quantity;
	private float costPrice;
	private float marketVal;
	private int remainingQuantity;
	private float pl;
	private float ltp;
	private int id;
	private String fyToken;
	private int exchange;
	private String isin;
	private String symbol;
	public String getHoldingType() {
		return holdingType;
	}
	public void setHoldingType(String holdingType) {
		this.holdingType = holdingType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}
	public float getMarketVal() {
		return marketVal;
	}
	public void setMarketVal(float marketVal) {
		this.marketVal = marketVal;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public float getPl() {
		return pl;
	}
	public void setPl(float pl) {
		this.pl = pl;
	}
	public float getLtp() {
		return ltp;
	}
	public void setLtp(float ltp) {
		this.ltp = ltp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFyToken() {
		return fyToken;
	}
	public void setFyToken(String fyToken) {
		this.fyToken = fyToken;
	}
	public int getExchange() {
		return exchange;
	}
	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	
}
