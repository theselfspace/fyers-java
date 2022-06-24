package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class ResponseOrder {

	private String orderDateTime;
	private String id;
	private String exchOrdId;
	private int side;
	private int segment;
	private int instrument;
	private String productType;
	private int status;
	private int qty;
	private int remainingQuantity;
	private int filledQty;
	private float limitPrice;
	private float stopPrice;
	private int type;
	private int discloseQty;
	private int dqQtyRem;
	private String orderValidity;
	private String source;
	private int slNo;
	private String fyToken;
	private boolean offlineOrder;
	private String message;
	private String orderNumStatus;
	private float tradedPrice;
	private int exchange;
	private String pan;
	private String clientId;
	private String symbol;
	private float ch;
	private float chp;
	private float lp;
	private String ex_sym;
	private String description;
	
	public String getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExchOrdId() {
		return exchOrdId;
	}
	public void setExchOrdId(String exchOrdId) {
		this.exchOrdId = exchOrdId;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public int getSegment() {
		return segment;
	}
	public void setSegment(int segment) {
		this.segment = segment;
	}
	public int getInstrument() {
		return instrument;
	}
	public void setInstrument(int instrument) {
		this.instrument = instrument;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public int getFilledQty() {
		return filledQty;
	}
	public void setFilledQty(int filledQty) {
		this.filledQty = filledQty;
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
	public int getDiscloseQty() {
		return discloseQty;
	}
	public void setDiscloseQty(int discloseQty) {
		this.discloseQty = discloseQty;
	}
	public int getDqQtyRem() {
		return dqQtyRem;
	}
	public void setDqQtyRem(int dqQtyRem) {
		this.dqQtyRem = dqQtyRem;
	}
	public String getOrderValidity() {
		return orderValidity;
	}
	public void setOrderValidity(String orderValidity) {
		this.orderValidity = orderValidity;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getFyToken() {
		return fyToken;
	}
	public void setFyToken(String fyToken) {
		this.fyToken = fyToken;
	}
	public boolean isOfflineOrder() {
		return offlineOrder;
	}
	public void setOfflineOrder(boolean offlineOrder) {
		this.offlineOrder = offlineOrder;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderNumStatus() {
		return orderNumStatus;
	}
	public void setOrderNumStatus(String orderNumStatus) {
		this.orderNumStatus = orderNumStatus;
	}
	public float getTradedPrice() {
		return tradedPrice;
	}
	public void setTradedPrice(float tradedPrice) {
		this.tradedPrice = tradedPrice;
	}
	public int getExchange() {
		return exchange;
	}
	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getCh() {
		return ch;
	}
	public void setCh(float ch) {
		this.ch = ch;
	}
	public float getChp() {
		return chp;
	}
	public void setChp(float chp) {
		this.chp = chp;
	}
	public float getLp() {
		return lp;
	}
	public void setLp(float lp) {
		this.lp = lp;
	}
	public String getEx_sym() {
		return ex_sym;
	}
	public void setEx_sym(String ex_sym) {
		this.ex_sym = ex_sym;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
