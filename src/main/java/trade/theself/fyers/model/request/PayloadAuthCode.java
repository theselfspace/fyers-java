package trade.theself.fyers.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author theselfspace
 *
 */
@JsonSerialize
public class PayloadAuthCode implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@JsonProperty("grant_type")
	@SerializedName(value = "grant_type")
	private String grantType = "authorization_code";
	
	private String appIdHash;
	
	private String code;

	public String getGrantType() {
		return grantType;
	}

	public String getAppIdHash() {
		return appIdHash;
	}

	public String getCode() {
		return code;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public void setAppIdHash(String appIdHash) {
		this.appIdHash = appIdHash;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
