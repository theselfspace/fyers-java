package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class MarketDepth {

	private int totalbuyqty;
	private int totalsellqty;
	private MDBidsAsks[] bids;
	private MDBidsAsks[] ask;
	private float o;
	private float h;
	private float i;
	private float c;
	private float chp;
	private float ch;
	private int itq;
	private int itt;
	private float ltp;
	private int v;
	private float atp;
	private float lower_ckt;
	private float upper_ckt;
	private String expiry;
	private boolean oiflag;
	private int pdoi;
	private float oipercent;
	public int getTotalbuyqty() {
		return totalbuyqty;
	}
	public void setTotalbuyqty(int totalbuyqty) {
		this.totalbuyqty = totalbuyqty;
	}
	public int getTotalsellqty() {
		return totalsellqty;
	}
	public void setTotalsellqty(int totalsellqty) {
		this.totalsellqty = totalsellqty;
	}
	public MDBidsAsks[] getBids() {
		return bids;
	}
	public void setBids(MDBidsAsks[] bids) {
		this.bids = bids;
	}
	public MDBidsAsks[] getAsk() {
		return ask;
	}
	public void setAsk(MDBidsAsks[] ask) {
		this.ask = ask;
	}
	public float getO() {
		return o;
	}
	public void setO(float o) {
		this.o = o;
	}
	public float getH() {
		return h;
	}
	public void setH(float h) {
		this.h = h;
	}
	public float getI() {
		return i;
	}
	public void setI(float i) {
		this.i = i;
	}
	public float getC() {
		return c;
	}
	public void setC(float c) {
		this.c = c;
	}
	public float getChp() {
		return chp;
	}
	public void setChp(float chp) {
		this.chp = chp;
	}
	public float getCh() {
		return ch;
	}
	public void setCh(float ch) {
		this.ch = ch;
	}
	public int getItq() {
		return itq;
	}
	public void setItq(int itq) {
		this.itq = itq;
	}
	public int getItt() {
		return itt;
	}
	public void setItt(int itt) {
		this.itt = itt;
	}
	public float getLtp() {
		return ltp;
	}
	public void setLtp(float ltp) {
		this.ltp = ltp;
	}
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
	public float getAtp() {
		return atp;
	}
	public void setAtp(float atp) {
		this.atp = atp;
	}
	public float getLower_ckt() {
		return lower_ckt;
	}
	public void setLower_ckt(float lower_ckt) {
		this.lower_ckt = lower_ckt;
	}
	public float getUpper_ckt() {
		return upper_ckt;
	}
	public void setUpper_ckt(float upper_ckt) {
		this.upper_ckt = upper_ckt;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public boolean isOiflag() {
		return oiflag;
	}
	public void setOiflag(boolean oiflag) {
		this.oiflag = oiflag;
	}
	public int getPdoi() {
		return pdoi;
	}
	public void setPdoi(int pdoi) {
		this.pdoi = pdoi;
	}
	public float getOipercent() {
		return oipercent;
	}
	public void setOipercent(float oipercent) {
		this.oipercent = oipercent;
	}
	
	
}
