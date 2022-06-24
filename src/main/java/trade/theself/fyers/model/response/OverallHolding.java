package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class OverallHolding {
	
	private int count_total;
	private float total_investment;
	private float total_current_value;
	private float total_pl;
	private float pnl_perc;
	
	public int getCount_total() {
		return count_total;
	}
	public void setCount_total(int count_total) {
		this.count_total = count_total;
	}
	public float getTotal_investment() {
		return total_investment;
	}
	public void setTotal_investment(float total_investment) {
		this.total_investment = total_investment;
	}
	public float getTotal_current_value() {
		return total_current_value;
	}
	public void setTotal_current_value(float total_current_value) {
		this.total_current_value = total_current_value;
	}
	public float getTotal_pl() {
		return total_pl;
	}
	public void setTotal_pl(float total_pl) {
		this.total_pl = total_pl;
	}
	public float getPnl_perc() {
		return pnl_perc;
	}
	public void setPnl_perc(float pnl_perc) {
		this.pnl_perc = pnl_perc;
	}
	
	
}
