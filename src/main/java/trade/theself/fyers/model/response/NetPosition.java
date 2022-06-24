package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class NetPosition {

	private int netQty;
	private int qty;
	private int avgPrice;
	private float netAvg;
	private int side;
	private String productType;
	private float realized_profit;
	private float unrealized_profit;
	private float pl;
	private float ltp;
	private int buyQty;
	private float buyAvg;
	private float buyVal;
	private int sellQty;
	private float sellAvg;
	private float sellVal;
	private int slNo;
	private String fyToken;
	private String crossCurrency;
	private float rbiRefRate;
	private float qtyMulti_com;
	private int segment;
	private String symbol;
	private String id;

	public int getNetQty() {
		return netQty;
	}

	public void setNetQty(int netQty) {
		this.netQty = netQty;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	public float getNetAvg() {
		return netAvg;
	}

	public void setNetAvg(float netAvg) {
		this.netAvg = netAvg;
	}

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

	public float getRealized_profit() {
		return realized_profit;
	}

	public void setRealized_profit(float realized_profit) {
		this.realized_profit = realized_profit;
	}

	public float getUnrealized_profit() {
		return unrealized_profit;
	}

	public void setUnrealized_profit(float unrealized_profit) {
		this.unrealized_profit = unrealized_profit;
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

	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public float getBuyAvg() {
		return buyAvg;
	}

	public void setBuyAvg(float buyAvg) {
		this.buyAvg = buyAvg;
	}

	public float getBuyVal() {
		return buyVal;
	}

	public void setBuyVal(float buyVal) {
		this.buyVal = buyVal;
	}

	public int getSellQty() {
		return sellQty;
	}

	public void setSellQty(int sellQty) {
		this.sellQty = sellQty;
	}

	public float getSellAvg() {
		return sellAvg;
	}

	public void setSellAvg(float sellAvg) {
		this.sellAvg = sellAvg;
	}

	public float getSellVal() {
		return sellVal;
	}

	public void setSellVal(float sellVal) {
		this.sellVal = sellVal;
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

	public String getCrossCurrency() {
		return crossCurrency;
	}

	public void setCrossCurrency(String crossCurrency) {
		this.crossCurrency = crossCurrency;
	}

	public float getRbiRefRate() {
		return rbiRefRate;
	}

	public void setRbiRefRate(float rbiRefRate) {
		this.rbiRefRate = rbiRefRate;
	}

	public float getQtyMulti_com() {
		return qtyMulti_com;
	}

	public void setQtyMulti_com(float qtyMulti_com) {
		this.qtyMulti_com = qtyMulti_com;
	}

	public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
