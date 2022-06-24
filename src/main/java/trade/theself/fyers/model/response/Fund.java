package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class Fund {

	private int id;
	private String title;
	private float equityAmount;
	private int commodityAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getEquityAmount() {
		return equityAmount;
	}
	public void setEquityAmount(float equityAmount) {
		this.equityAmount = equityAmount;
	}
	public int getCommodityAmount() {
		return commodityAmount;
	}
	public void setCommodityAmount(int commodityAmount) {
		this.commodityAmount = commodityAmount;
	}
	
	
}
