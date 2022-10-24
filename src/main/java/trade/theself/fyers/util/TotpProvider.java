package trade.theself.fyers.util;

import org.jboss.aerogear.security.otp.Totp;

/**
 * Class that generates generate TOTP
 * @author theselfspace
 *
 */
public class TotpProvider {

	private Totp totp = null;
	
	public TotpProvider(String secret) {
		totp = new Totp(secret);
	}
	
	public String getTotp() {
		return totp.now();
	}
}
