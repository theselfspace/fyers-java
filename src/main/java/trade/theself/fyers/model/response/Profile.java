package trade.theself.fyers.model.response;

/**
 * 
 * @author theselfspace
 *
 */
public class Profile {

	private String fy_id;
	private String name;
	private String image;
	private String display_name;
	private String pin_change_date;
	private String email_id;
	private String pwd_change_date;
	private String PAN;
	private int pwd_to_expire;
	public String getFy_id() {
		return fy_id;
	}
	public void setFy_id(String fy_id) {
		this.fy_id = fy_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getPin_change_date() {
		return pin_change_date;
	}
	public void setPin_change_date(String pin_change_date) {
		this.pin_change_date = pin_change_date;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPwd_change_date() {
		return pwd_change_date;
	}
	public void setPwd_change_date(String pwd_change_date) {
		this.pwd_change_date = pwd_change_date;
	}
	public String getPAN() {
		return PAN;
	}
	public void setPAN(String pAN) {
		PAN = pAN;
	}
	public int getPwd_to_expire() {
		return pwd_to_expire;
	}
	public void setPwd_to_expire(int pwd_to_expire) {
		this.pwd_to_expire = pwd_to_expire;
	}
	
	
}
