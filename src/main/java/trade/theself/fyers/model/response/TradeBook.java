package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class TradeBook {

	private String clientId;
	private String orderDateTime;
	private String orderNumber;
	private String exchangeOrderNo;
	private int exchange;
	private int transactionType;
	private int segment;
	private int orderType;
	private String fyToken;
	private String productType;
	private int tradedQty;
	private float tradePrice;
	private float tradeValue;
	private String tradeNumber;
	private String id;
	private int row;
	private String symbol;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getExchangeOrderNo() {
		return exchangeOrderNo;
	}

	public void setExchangeOrderNo(String exchangeOrderNo) {
		this.exchangeOrderNo = exchangeOrderNo;
	}

	public int getExchange() {
		return exchange;
	}

	public void setExchange(int exchange) {
		this.exchange = exchange;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getFyToken() {
		return fyToken;
	}

	public void setFyToken(String fyToken) {
		this.fyToken = fyToken;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getTradedQty() {
		return tradedQty;
	}

	public void setTradedQty(int tradedQty) {
		this.tradedQty = tradedQty;
	}

	public float getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(float tradePrice) {
		this.tradePrice = tradePrice;
	}

	public float getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(float tradeValue) {
		this.tradeValue = tradeValue;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
