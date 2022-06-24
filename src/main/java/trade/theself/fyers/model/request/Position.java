package trade.theself.fyers.model.request;

/**
 * 
 * @author theselfspace
 *
 */
public class Position {

	private String symbol;
	private int positionSide;
	private int convertQty;
	private String convertFrom;
	private String convertTo;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getPositionSide() {
		return positionSide;
	}
	public void setPositionSide(int positionSide) {
		this.positionSide = positionSide;
	}
	public int getConvertQty() {
		return convertQty;
	}
	public void setConvertQty(int convertQty) {
		this.convertQty = convertQty;
	}
	public String getConvertFrom() {
		return convertFrom;
	}
	public void setConvertFrom(String convertFrom) {
		this.convertFrom = convertFrom;
	}
	public String getConvertTo() {
		return convertTo;
	}
	public void setConvertTo(String convertTo) {
		this.convertTo = convertTo;
	}
	
	
}
