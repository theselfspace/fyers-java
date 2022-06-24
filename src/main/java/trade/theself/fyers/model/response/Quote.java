package trade.theself.fyers.model.response;

public class Quote {

	private float ch;
	private float chp;
	private float ip;
	private float spread;
	private float ask;
	private float bid;
	private float open_price;
	private float high_price;
	private float low_price;
	private int volume;
	private String short_name;
	private String exchange;
	private String description;
	private String original_name;
	private String symbol;
	private int tt;
	private QuoteDict cmd;
	
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
	public float getIp() {
		return ip;
	}
	public void setIp(float ip) {
		this.ip = ip;
	}
	public float getSpread() {
		return spread;
	}
	public void setSpread(float spread) {
		this.spread = spread;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public float getOpen_price() {
		return open_price;
	}
	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}
	public float getHigh_price() {
		return high_price;
	}
	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}
	public float getLow_price() {
		return low_price;
	}
	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getTt() {
		return tt;
	}
	public void setTt(int tt) {
		this.tt = tt;
	}
	public QuoteDict getCmd() {
		return cmd;
	}
	public void setCmd(QuoteDict cmd) {
		this.cmd = cmd;
	}
	
	
}
