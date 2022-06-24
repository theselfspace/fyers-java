package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class Candle {

	private long timestamp;
	private float open;
	private float high;
	private float low;
	private float close;
	private long vol;
	
	public Candle(long timestamp, float open, float high, float low, float close, long vol) {
		this.timestamp = timestamp;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.vol = vol;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public long getVol() {
		return vol;
	}
	public void setVol(long vol) {
		this.vol = vol;
	}
	
	
}
