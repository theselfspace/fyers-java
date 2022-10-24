package trade.theself.fyers.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import trade.theself.fyers.FyersFly;
import trade.theself.fyers.boilerplate.LoginHandler;
import trade.theself.fyers.exception.CommandException;
import trade.theself.fyers.exception.LoginException;
import trade.theself.fyers.model.response.Fund;
import trade.theself.fyers.model.response.Holding;
import trade.theself.fyers.model.response.MarketStatus;
import trade.theself.fyers.model.response.Profile;
import trade.theself.fyers.util.FyerParam;
import trade.theself.fyers.util.TotpProvider;

public class FyersFlyTest {

	private FyersFly fly;
	private Gson gson = new Gson();
	private final boolean DISABLE_TEST = true; 
	private TotpProvider totp = null;
	
	@Before
	public void setUp() throws LoginException {
		totp = new TotpProvider("<YOUR TOTP KEY>");		
		if(DISABLE_TEST) {
			System.err.println("Tests are disabled");
			return;
		}
		try {
			System.out.println(totp.getTotp());
		} catch (Exception e) {
			System.err.println("Please set your TOTP key");
			return;
		}
		this.getToken();
	}

	@Test
	public void testAll() throws CommandException {
		if(DISABLE_TEST) {
			System.err.println("Tests are disabled");
			return;
		}			
		System.out.println("GET HOLDING");
		Holding[] holdings = fly.getHolding();
		System.out.println(gson.toJson(holdings));
		System.out.println();
		
		System.out.println("GET FUND");
		Fund[] funds = fly.getFund(); 
		System.out.println(gson.toJson(funds));
		System.out.println();

		System.out.println("GET PROFILE");
		Profile profile = fly.getProfile();
		System.out.println(gson.toJson(profile));
		System.out.println();

		System.out.println("GET MARKET STATUS");
		MarketStatus[] status = fly.getMarketStatus();
		System.out.println(gson.toJson(status));
		System.out.println();

		System.out.println("GET TRADE BOOK");
		System.out.println(gson.toJson(fly.getTradeBook()));
		System.out.println();

		System.out.println(gson.toJson(fly.getQuotes("NSE:SBIN-EQ,NSE:TATAMOTORS-EQ")));
		
		System.out.println(gson.toJson(fly.getMarketDepth("NSE:SBIN-EQ", 1)));
		
		System.out.println("BUY INSTRUMENT");
		//NOTE: please note the format for the instrument name
		//			<EXCHANGE>:<INSTRUMENT>
		//fly.placeBuyOrder("NSE:NIFTY2262315300PE", 50, FyerParam.ORDER_TYPE_MARKET, FyerParam.ORDER_PRODUCTTYPE_INTRADAY);
		
		System.out.println("SELL BY ID");
		//NOTE: please note the format of insturment name, this should follow below naming convention 
		//			<EXCHANGE>:<INSTRUMENT>-<PRODUCT TYPE>
		//fly.placeExitPositionOrderById("NSE:NIFTY2262315300PE-INTRADAY");
	}

	private String getToken() throws LoginException {
		if(fly != null && StringUtils.isNotEmpty(fly.getAccessToken()))
			return fly.getAccessToken();
		LoginHandler loginHandler = new LoginHandler();
		loginHandler.setClientCode("<YOUR APP ID>");
		loginHandler.setRedirectUrl("<YOUR APP SPECIFIC REDIRECT URL>");
		String authCode = loginHandler.login("<LOGIN USERNAME>", "<4 DIGIT MFA>", totp.getTotp());
		System.out.println("Auth code:"+authCode);
		fly = new FyersFly("<YOUR APP ID>", "<YOUR APP SECRET>");
		String token = fly.generateAccessToken(authCode);
		System.out.println("Access token: "+ token);
		fly.setAccessToken(token);		
		return token;
	}

	
}
