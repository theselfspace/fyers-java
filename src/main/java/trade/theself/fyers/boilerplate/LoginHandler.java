package trade.theself.fyers.boilerplate;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.github.bonigarcia.wdm.WebDriverManager;
import trade.theself.fyers.exception.LoginException;

/**
 * Class responsible for chromedriver login actions
 * @author theselfspace
 *
 */
public class LoginHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(LoginHandler.class);
	
	private int THREAD_SLEEP_DURATION = 2000;
	private String authCode = StringUtils.EMPTY;
	private String loginUrl = "https://login.fyers.in/?cb=https://myapi.fyers.in/dashboard";
	private String clientCode = StringUtils.EMPTY;
	private String redirectUrl = StringUtils.EMPTY;
	private String state = "sample_state";
	
	/**
	 * Login to the portal through browser path.. This could break if Fyers decide to update their UI
	 * @param clientId - Client ID of the user
	 * @param otp - 4 digits wide OTP to be used for login
	 * @param totp - 6 digits wide TOTP to be used for 2fa
	 * @return - authorization code on successful login
	 * @throws LoginException - {@link LoginException} in case of failure
	 */
	public String login(String clientId, String otp, String totp) throws LoginException {
		
		this.authCode = StringUtils.EMPTY;
		
		if(StringUtils.isEmpty(clientId)) {
			throw new LoginException("Client ID is null!");
		}
		if(StringUtils.isEmpty(otp) || otp.length()!=4 || !NumberUtils.isCreatable(otp)) {
			throw new LoginException("Either OTP is missing or it's format is wrong");
		}
		if(StringUtils.isEmpty(totp) || totp.length()!=6 || !NumberUtils.isCreatable(totp)) {
			throw new LoginException("Either TOTP is missing or it's format is wrong");
		}
		if(StringUtils.isEmpty(clientCode) || StringUtils.isEmpty(redirectUrl)) {
			throw new LoginException("Client code [which is the app id in the portal] and redirect url are mandatory. Please check your app configuration details.");
		}
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(
				"--headless",
				"--disable-gpu", 
				"--ignore-certificate-errors",
				"--disable-extensions",
				"--no-sandbox",
				"--disable-dev-shm-usage",
				"--incognito");
		
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get(loginUrl);

		By userid = By.id("fy_client_id");
		By clientIdSubmit = By.id("clientIdSubmit");
		
		wait.until(presenceOfElementLocated(userid));
		driver.findElement(userid).sendKeys(clientId);
		wait.until(elementToBeClickable(clientIdSubmit));
		driver.findElement(clientIdSubmit).click();
		
		try {
			Thread.sleep(THREAD_SLEEP_DURATION);
		} catch (InterruptedException e) {
			throw new LoginException(e.getMessage());
		}
		
		
		By first = By.id("first");
		By second = By.id("second");
		By third = By.id("third");
		By fourth = By.id("fourth");
		By fifth = By.id("fifth");
		By sixth = By.id("sixth");
		By confirmOtpSubmit = By.id("confirmOtpSubmit");
		wait.until(presenceOfElementLocated(first));
		
		driver.findElement(first).sendKeys(String.valueOf(totp.charAt(0)));
		driver.findElement(second).sendKeys(String.valueOf(totp.charAt(1)));
		driver.findElement(third).sendKeys(String.valueOf(totp.charAt(2)));
		driver.findElement(fourth).sendKeys(String.valueOf(totp.charAt(3)));
		driver.findElement(fifth).sendKeys(String.valueOf(totp.charAt(4)));
		driver.findElement(sixth).sendKeys(String.valueOf(totp.charAt(5)));
		
		driver.findElement(confirmOtpSubmit).click();
		
		try {
			Thread.sleep(THREAD_SLEEP_DURATION);
		} catch (InterruptedException e) {
			throw new LoginException(e.getMessage());
		}
		
		wait.until(presenceOfElementLocated(first));
		By firstXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[1]");
		By secondXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[2]");
		By thirdXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[3]");
		By fourthXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[4]");
		
		driver.findElement(firstXpath).sendKeys(Character.toString(otp.charAt(0)));
		driver.findElement(secondXpath).sendKeys(Character.toString(otp.charAt(1)));
		driver.findElement(thirdXpath).sendKeys(Character.toString(otp.charAt(2)));
		driver.findElement(fourthXpath).sendKeys(Character.toString(otp.charAt(3)));

		try {
			Thread.sleep(THREAD_SLEEP_DURATION);
		} catch (InterruptedException e) {
			throw new LoginException(e.getMessage());
		}
		
		//login is successful.. we can now hit the url to get the auth code
		String authCodeUrl = "https://api.fyers.in/api/v2/generate-authcode?client_id="+clientCode+"&redirect_uri="+redirectUrl+"&response_type=code&state="+state;
		driver.get(authCodeUrl);
		
		try {
			Thread.sleep(THREAD_SLEEP_DURATION);
		} catch (InterruptedException e) {
			throw new LoginException(e.getMessage());
		}
		
		String currentUrl = driver.getCurrentUrl();
		LOGGER.debug("CreateLoginSession: Current URL: "+ currentUrl);
		
		if (driver != null) {
			driver.quit();
		}
		
		
		if(StringUtils.isNotEmpty(currentUrl)){
			try {
				List<NameValuePair> params = URLEncodedUtils.parse(new URI(currentUrl), Charset.forName("UTF-8"));
				for (NameValuePair param : params) {
					if(param.getName().equals("auth_code"))
						authCode = param.getValue();
						break; 
				}
			} catch (URISyntaxException e) {
				throw new LoginException(e.getMessage());
			}

			
		} else {
			LOGGER.error("Could not auth with Fyers.. ");
			throw new LoginException("Could not auth with Fyers.. ");
		}
		
		return authCode;
	}
	
	/**
	 * Get the auth code
	 * @return - the auth code
	 */
	public String getAuthCode() {
		return this.authCode;
	}
	
	/**
	 * Set how long you would like to sleep between screen refresh
	 * @param duration - sleep duration
	 */
	public void setThreadSleep(int duration) {
		this.THREAD_SLEEP_DURATION = duration;
	}

	/**
	 * set the client code
	 * @param clientCode - the client code
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * set the redirect URL
	 * @param redirectUrl - the redirect URL
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * Set the state that you would like the login action to return back
	 * @param state - the state
	 */
	public void setState(String state) {
		this.state = state;
	}

}
