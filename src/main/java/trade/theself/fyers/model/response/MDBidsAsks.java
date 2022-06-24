package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class MDBidsAsks {

	private float price;
    private int volume;
    private int ord;
    
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
    
    
}
