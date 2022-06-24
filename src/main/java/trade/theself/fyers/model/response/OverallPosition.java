package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class OverallPosition {

	private int count_total;
	private int count_open;
	private float pl_total;
	private float pl_realized;
	private float pl_unrealized;

	public int getCount_total() {
		return count_total;
	}

	public void setCount_total(int count_total) {
		this.count_total = count_total;
	}

	public int getCount_open() {
		return count_open;
	}

	public void setCount_open(int count_open) {
		this.count_open = count_open;
	}

	public float getPl_total() {
		return pl_total;
	}

	public void setPl_total(float pl_total) {
		this.pl_total = pl_total;
	}

	public float getPl_realized() {
		return pl_realized;
	}

	public void setPl_realized(float pl_realized) {
		this.pl_realized = pl_realized;
	}

	public float getPl_unrealized() {
		return pl_unrealized;
	}

	public void setPl_unrealized(float pl_unrealized) {
		this.pl_unrealized = pl_unrealized;
	}

}
