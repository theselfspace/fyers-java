package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class EdisDetail {

	private String clientId; 
	private String isin; 
	private float qty; 
	private float qtyUtlize; 
	private String entryDate; 
	private String startDate; 
	private String endDate; 
	private int noOfDays; 
	private String source; 
	private String status; 
	private String reason; 
	private String internalTxnId; 
	private String dpTxnId; 
	private String errCode;
	private String errorCount; 
	private String transactionId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getQtyUtlize() {
		return qtyUtlize;
	}
	public void setQtyUtlize(float qtyUtlize) {
		this.qtyUtlize = qtyUtlize;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getInternalTxnId() {
		return internalTxnId;
	}
	public void setInternalTxnId(String internalTxnId) {
		this.internalTxnId = internalTxnId;
	}
	public String getDpTxnId() {
		return dpTxnId;
	}
	public void setDpTxnId(String dpTxnId) {
		this.dpTxnId = dpTxnId;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
