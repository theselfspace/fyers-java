package trade.theself.fyers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import trade.theself.fyers.exception.CommandException;
import trade.theself.fyers.exception.LoginException;
import trade.theself.fyers.model.request.EdisInquiry;
import trade.theself.fyers.model.request.EdisRecords;
import trade.theself.fyers.model.request.RequestOrder;
import trade.theself.fyers.model.request.PayloadAuthCode;
import trade.theself.fyers.model.request.Position;
import trade.theself.fyers.model.request.SellOrder;
import trade.theself.fyers.model.request.SellOrderBySymbol;
import trade.theself.fyers.model.request.UpdateOrder;
import trade.theself.fyers.model.response.Candle;
import trade.theself.fyers.model.response.EdisDetail;
import trade.theself.fyers.model.response.EdisInquiryData;
import trade.theself.fyers.model.response.Fund;
import trade.theself.fyers.model.response.Holding;
import trade.theself.fyers.model.response.MarketDepth;
import trade.theself.fyers.model.response.MarketStatus;
import trade.theself.fyers.model.response.NetPosition;
import trade.theself.fyers.model.response.OverallPosition;
import trade.theself.fyers.model.response.Profile;
import trade.theself.fyers.model.response.Quote;
import trade.theself.fyers.model.response.ResponseOrder;
import trade.theself.fyers.model.response.TradeBook;
import trade.theself.fyers.util.FyerParam;
import trade.theself.fyers.util.HttpDeleteWithBody;

/**
 * Main class that implements all the service calls
 * @author theselfspace
 *
 */
public class FyersFly {


	private String APPID = ""; 
	private String SECRET = ""; 
	private static final String REDIRECT_URL 		= "https://myapi.fyers.in/";
	private static final String AUTHCODE_URL 		= "https://api.fyers.in/api/v2/validate-authcode";
	private static final String PROFILE_URL 		= "https://api.fyers.in/api/v2/profile";
	private static final String FUND_URL 			= "https://api.fyers.in/api/v2/funds";
	private static final String HOLDING_URL 		= "https://api.fyers.in/api/v2/holdings";
	private static final String MARKETSTATUS_URL 	= "https://api.fyers.in/api/v2/market-status";
	private static final String ORDERS_URL 			= "https://api.fyers.in/api/v2/orders";
	private static final String MULTI_ORDERS_URL 	= "https://api.fyers.in/api/v2/orders-multi";
	private static final String POSITIONS_URL 		= "https://api.fyers.in/api/v2/positions";
	private static final String TRADEBOOK_URL 		= "https://api.fyers.in/api/v2/tradebook";
	private static final String HISTORICAL_URL 		= "https://api.fyers.in/data-rest/v2/history/?symbol=%s&resolution=%s&date_format=%s&range_from=%s&range_to=%s&cont_flag=%s";
	private static final String QUOTE_URL 			= "https://api.fyers.in/data-rest/v2/quotes/?symbols=%s";
	private static final String MARKET_DEPTH_URL 	= "https://api.fyers.in/data-rest/v2/depth/?symbol=%s&ohlcv_flag=%s";
	private static final String EDIS_DETAILS_URL 	= "https://api.fyers.in/api/v2/details";
	private static final String EDIS_INDEX_URL 		= "https://api.fyers.in/api/v2/index";
	private static final String EDIS_INQUIRY_URL 	= "https://api.fyers.in/api/v2/inquiry";
	private static final String EDIS_TPIN_GENERATE_URL = "https://api.fyers.in/api/v2/tpin";
	
	private static final String VERIFY_TOKEN_URL 	= "https://api.fyers.in/vagator/v1/verify_token";
	

	private String accessToken = null;
	private JsonParser jsonParser = new JsonParser();
	private Gson gson = new Gson();

	private Map<String, String> orderidMap = new ConcurrentHashMap<>();

	/**
	 * Create the instance
	 * @param appid - app id as per api configuration
	 * @param secret - secret key as per api configuration
	 */
	public FyersFly(String appid, String secret) {
		this.APPID = appid;
		this.SECRET = secret;
	}
	
	/**
	 * Method to generate the access token 
	 * @param authCode - auth code that was created after successful login
	 * @return - access token
	 * @throws LoginException - exception in case of failure
	 */
	public String generateAccessToken(String authCode) throws LoginException {

		if (StringUtils.isEmpty(authCode)) {
			throw new LoginException("Auth code missing");
		}

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(AUTHCODE_URL);
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity params;

		PayloadAuthCode payload = new PayloadAuthCode();
		payload.setAppIdHash(getHash());
		payload.setCode(authCode);

		try {
			params = new StringEntity(gson.toJson(payload));

		} catch (UnsupportedEncodingException e) {
			throw new LoginException(e.getMessage());
		}
		httpPost.setEntity(params);

		try {
			CloseableHttpResponse response = client.execute(httpPost);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			client.close();
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			JsonElement jsonElement = jsonParser.parse(sb.toString());
			this.accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();
			return this.accessToken;
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	/**
	 * Method that creates the hash code considering the app ID and the associated secret value
	 * @return - String hash
	 */
	private String getHash() {
		return Hashing.sha256().hashString(APPID + ":" + SECRET, StandardCharsets.UTF_8).toString();
	}

	public String getAccessToken() {
		return this.accessToken;
	}
	
	/**
	 * API to get the User profile
	 * @return - {@link Profile} details of the user
	 * @throws CommandException - exception in case of failure
	 */
	public Profile getProfile() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(PROFILE_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("data"), Profile.class);

	}

	/**
	 * API to get the fund details
	 * @return {@link Fund} array that holds the fund details 
	 * @throws CommandException - exception in case of failure
	 */
	public Fund[] getFund() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(FUND_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("fund_limit"), Fund[].class);
	}

	/**
	 * Method to get user's current holding
	 * @return {@link Holding} of the user
	 * @throws CommandException - exception in case of failure
	 */
	public Holding[] getHolding() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(HOLDING_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("holdings"), Holding[].class);
	}

	/**
	 * Get status of all the monitored markets
	 * @return {@link MarketStatus} that provides information of all the markets
	 * @throws CommandException - exception in case of failure
	 */
	public MarketStatus[] getMarketStatus() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(MARKETSTATUS_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);

		return gson.fromJson(jsonObject.get("marketStatus"), MarketStatus[].class);
	}

	/**
	 * Method to get order details
	 * @return {@link ResponseOrder}
	 * @throws CommandException - exception in case of failure
	 */
	public ResponseOrder[] getOrders() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(ORDERS_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("orderBook"), ResponseOrder[].class);
	}

	/**
	 * Get order by ID
	 * @param id - Order id
	 * @return {@link ResponseOrder}
	 * @throws CommandException - exception in case of failure
	 */
	public ResponseOrder[] getOrder(String id) throws CommandException {
		if (StringUtils.isEmpty(id)) {
			throw new CommandException("Order ID is null");
		}
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(ORDERS_URL + "?id=" + id);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("orderBook"), ResponseOrder[].class);
	}

	/**
	 * Get net position
	 * @return {@link NetPosition} of the user
	 * @throws CommandException - exception in case of failure
	 */
	public NetPosition[] getNetPositions() throws CommandException {
		// POSITIONS_URL
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(POSITIONS_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("netPositions"), NetPosition[].class);
	}

	/**
	 * Get overall position
	 * @return {@link OverallPosition} of the user
	 * @throws CommandException - exception in case of failure
	 */
	public OverallPosition getOverallPosition() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(POSITIONS_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("overall"), OverallPosition.class);
	}

	/**
	 * Get trade book
	 * @return {@link TradeBook} of the user
	 * @throws CommandException - exception in case of failure
	 */
	public TradeBook[] getTradeBook() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(TRADEBOOK_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);
		return gson.fromJson(jsonObject.get("tradeBook"), TradeBook[].class);

	}

	/**
	 * Place buy order
	 * @param symbol - instrument symbol
	 * @param quantity - how may to buy
	 * @param type - order type, FyerParam.ORDER_TYPE_MARKET 
	 * @param productType - What type of product it is, ex FyerParam.ORDER_PRODUCTTYPE_INTRADAY
	 * @return  Orderid
	 * @throws CommandException - exception in case of failure
	 */
	public String placeBuyOrder(String symbol, int quantity, int type, String productType) throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(symbol) || quantity<1 || type<1 || type>4 || StringUtils.isEmpty(productType)) {
			throw new CommandException("Invalid input");
		}
		//TODO - add order type wise validation as well.. defined at https://myapi.fyers.in/docs/#section/Order-Placement-Guide
		
		RequestOrder order = new RequestOrder();
		order.setSymbol(symbol);
		order.setQty(quantity);
		order.setType(type);
		order.setSide(FyerParam.ORDER_SIDE_BUY);
		order.setProductType(productType);
		order.setValidity(FyerParam.ORDER_VALIDITY_IOC);

		JsonObject resp = this.executeCommandPost(ORDERS_URL, gson.toJson(order));
		if(resp.has("id"))
			orderidMap.put(symbol, resp.get("id").getAsString());
		else
			throw new CommandException("Could not find order id in the response. "+ resp);
		return resp.get("id").getAsString();
	}
	
	/**
	 * Between buy and sell, its the 'side' parameter that will differentiate
	 * whether this is to enter the position or exit the position
	 * 
	 * @param symbol - instrument symbol 
	 * @param quantity - how many to buy
	 * @param type - order type, ex FyerParam.ORDER_TYPE_MARKET
	 * @param productType - What type of product it is, ex FyerParam.ORDER_PRODUCTTYPE_INTRADAY
	 * @param side - Whether this buy side (1) or sell side (-1)
	 * @param disclosedQty - disclosed quantity
	 * @throws CommandException - - exception in case of failure
	 */
	public void placeOrder(String symbol, int quantity, int type, String productType, int side, int disclosedQty)
			throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(symbol) || quantity<1 || type<1 || type>4 || StringUtils.isEmpty(productType) || (side!=1 && side!=-1) || disclosedQty<0) {
			throw new CommandException("Invalid input");
		}
		//TODO - add order type wise validation as well.. defined at https://myapi.fyers.in/docs/#section/Order-Placement-Guide
		
		RequestOrder order = new RequestOrder();
		order.setSymbol(symbol);
		order.setQty(quantity);
		order.setType(type);
		order.setSide(side);
		order.setProductType(productType);
		order.setValidity(FyerParam.ORDER_VALIDITY_IOC);
		if (disclosedQty > 0) {
			order.setDisclosedQty(disclosedQty);
		}
		this.executeCommandPost(ORDERS_URL, gson.toJson(order));

	}

	/**
	 * Exit the position either by id or instrument symbol
	 * 
	 * @param id - Instrument symbol
	 * @throws CommandException - exception in case of failure
	 */
	public void placeExitPositionOrderById(String id) throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(id))
			throw new CommandException("Invalid input, order id missing.");
		SellOrder order = new SellOrder();
		order.setId(id);
		JsonObject resp = this.executeCommandDelete(POSITIONS_URL, gson.toJson(order));
		if (resp.get("s").getAsString().equals("ok"))
			orderidMap.remove(id);
		else 
			throw new CommandException("Could not place exit order for id "+ id+", "+ resp);
	}

	/**
	 * Place exit order by instrument symbol
	 * @param symbol - instrument symbol
	 * @throws CommandException - exception in case of failure
	 */
	public void placeExitPositionOrderBySymbol(String symbol) throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(symbol))
			throw new CommandException("Invalid input, instrument symbol missing.");

		SellOrderBySymbol order = new SellOrderBySymbol();
		order.setSymbol(symbol);
		JsonObject resp = this.executeCommandDelete(POSITIONS_URL, gson.toJson(order));
		if (resp.get("s").getAsString().equals("ok"))
			orderidMap.remove(symbol);
		else 
			throw new CommandException("Could not place exit order for symbol "+ symbol+", "+ resp.getAsString());
	}

	/**
	 * Place order to exit all the positions
	 * @throws CommandException - exception in case of failure
	 */
	public void placeExitPositionOrderBySymbol() throws CommandException {
		this.validateToken();
		JsonObject resp = this.executeCommandDelete(POSITIONS_URL, "{}");
		if (!resp.get("s").getAsString().equals("ok"))
			throw new CommandException("Could not place exit all positions order , "+ resp.getAsString());
	}

	/**
	 * Place multiple orders in a single invocation
	 * @param orders - {@link RequestOrder} array
	 * @return - response json data
	 * @throws CommandException - exception in case of failure
	 */
	public String placeMultipleOrders(RequestOrder[] orders) throws CommandException {
		if(orders==null || orders.length>FyerParam.MULTI_ORDER_LIMIT) {
			throw new CommandException("Fyers multi order limit is "+FyerParam.MULTI_ORDER_LIMIT);
		}
		this.validateToken();
		JsonObject resp = this.executeCommandPost(MULTI_ORDERS_URL, gson.toJson(orders));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("data").getAsString();
		else
			throw new CommandException("Response of the failed command: "+ resp.getAsString());
	}
	
	/**
	 * Place order update
	 * @param order - {@link UpdateOrder} object to update
	 * @return - success message
	 * @throws CommandException - exception in case of failure
	 */
	public String placeUpdateOrder(UpdateOrder order) throws CommandException {
		this.validateToken();
		if(order==null) {
			throw new CommandException("Invalid input, order details missing.");			
		}
		JsonObject resp = this.executeCommandPut(ORDERS_URL, gson.toJson(order));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("message").getAsString();
		else
			throw new CommandException("Update failed: "+ resp.getAsString());
	}
	
	/**
	 * Places multiple order update command
	 * @param orders - {@link UpdateOrder} array with order details to be updated
	 * @return Response message in case of success 
	 * @throws CommandException - exception in case of failure
	 */
	public String placeMultipleUpdateOrder(UpdateOrder[] orders) throws CommandException {
		this.validateToken();
		if(orders==null || orders.length<1) {
			throw new CommandException("Invalid input. Orders details missing/");
		}
		JsonObject resp = this.executeCommandPut(MULTI_ORDERS_URL, gson.toJson(orders));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("data").getAsString();
		else
			throw new CommandException("Multiple Update failed: "+ resp.getAsString());
	}
	
	/**
	 * Places cancel order request
	 * @param order - {@link SellOrder} to be cancelled
	 * @return - message on successful order placement
	 * @throws CommandException - exception in case of failure
	 */
	public String placeCancelOrder(SellOrder order) throws CommandException {
		this.validateToken();
		if(order==null) {
			throw new CommandException("Invalid input. Sell order details missing.");
		}
		JsonObject resp = this.executeCommandDelete(ORDERS_URL, gson.toJson(order));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("message").getAsString();
		else
			throw new CommandException("Cancel failed: "+ resp.getAsString());
	}
	
	/**
	 * Places multiple order cancellation request
	 * @param orders - {@link SellOrder} array to be cancelled
	 * @return - message on successful order placement
	 * @throws CommandException - exception in case of failure
	 */
	public String placeMultipleCancelOrder(SellOrder[] orders) throws CommandException {
		this.validateToken();
		if(orders==null || orders.length<1) {
			throw new CommandException("Invalid input. Missing sell order details.");
		}
		JsonObject resp = this.executeCommandDelete(MULTI_ORDERS_URL, gson.toJson(orders));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("data").getAsString();
		else
			throw new CommandException("Cancel failed: "+ resp.getAsString());
	}
	
	/**
	 * Places convert position request
	 * @param position - {@link Position} to be converted
	 * @return - message on successful order placement
	 * @throws CommandException - exception in case of failure
	 */
	public String placeConvertPosition(Position position) throws CommandException {
		this.validateToken();
		if(position==null) {
			throw new CommandException("Invalid input. Missing position object details.");
		}
		JsonObject resp = this.executeCommandPut(POSITIONS_URL, gson.toJson(position));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("message").getAsString();
		else
			throw new CommandException("Convert position failed: "+ resp.getAsString());
	}
	
	/**
	 * Places exit position command
	 * @param position {@link SellOrder} position to exit
	 * @return - response message on successful order placement
	 * @throws CommandException - exception in case of failure
	 */
	public String placeExitPosition(SellOrder position) throws CommandException {
		this.validateToken();
		if(position==null) {
			throw new CommandException("Invalid input. Missing position object details.");
		}
		JsonObject resp = this.executeCommandPut(POSITIONS_URL, gson.toJson(position));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("message").getAsString();
		else
			throw new CommandException("Exit position failed: "+ resp.getAsString());
	}
	
	/**
	 * Places all position exit request
	 * @return - response message on successful order placement
	 * @throws CommandException - exception in case of failure
	 */
	public String placeExitAllPositions() throws CommandException {
		this.validateToken();
		JsonObject resp = this.executeCommandDelete(POSITIONS_URL, "{}");
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("message").getAsString();
		else
			throw new CommandException("Exit all positions failed: "+ resp.getAsString());
	}
	//work on multiple order get, market data apis

	/**
	 * Method to get the historical data
	 * @param instrument - instrument name to be pulled data for, ex NSE:SBIN-EQ
	 * @param candlePeriod - ex 1D for day or minute values like 1, 2, 3, 5, 10 etc upto 240 
	 * @param dateFormat -  0 for epoch, 1 for yyyy-mm-dd format date
	 * @param startDate - the start date of records
	 * @param endDate - the end date of records
	 * @param cont - 1 for continues data and future options
	 * @return List of {@link Candle}
	 * @throws CommandException - exception in case of failure
	 */
	public List<Candle> getHistoricalData(String instrument, String candlePeriod, String dateFormat, String startDate, String endDate, String cont) throws CommandException{
		List<Candle> candles = new ArrayList<>();
		this.validateToken();
		if(StringUtils.isEmpty(instrument) || StringUtils.isEmpty(candlePeriod) || StringUtils.isEmpty(dateFormat) 
				|| StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate) || StringUtils.isEmpty(cont))
			throw new CommandException("Invalid input");
		
		HttpGet httpGet = this.buildGetHeader(String.format(HISTORICAL_URL, instrument, candlePeriod, dateFormat, startDate, endDate, cont));
		JsonObject jsonObject = this.executeCommand(httpGet);
		
		Iterator<JsonElement> elementI = jsonObject.get("candles").getAsJsonArray().iterator();
		while(elementI.hasNext()) {
			JsonElement next = elementI.next();
			JsonArray d = next.getAsJsonArray();
			candles.add(new Candle(
					d.get(0).getAsLong(), 
					d.get(1).getAsFloat(),
					d.get(2).getAsFloat(),
					d.get(3).getAsFloat(),
					d.get(4).getAsFloat(),
					d.get(5).getAsLong()
				));
		}
		return candles;

	}
	
	/**
	 * Get Quotes data
	 * @param instrument - instrument id to get the quotes for
	 * @return - {@link Quote} of the passed instrument id
	 * @throws CommandException - exception in case of failure
	 */
	public Quote getQuote(String instrument) throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(instrument))
			throw new CommandException("Invalid input");
		HttpGet httpGet = this.buildGetHeader(String.format(QUOTE_URL, instrument));
		JsonObject jsonObject = this.executeCommand(httpGet);
		if(jsonObject.get("d").getAsJsonArray().get(0).getAsJsonObject().get("s").getAsString().equals("ok"))
			return gson.fromJson(jsonObject.get("d").getAsJsonArray().get(0).getAsJsonObject().get("v"), Quote.class) ;
		else
			return gson.fromJson("", Quote.class) ;
	}
	
	/**
	 * Quotes for the instruments passed as comma separated string
	 * @param instruments - comma separated instruments
	 * @return {@link Quote} of passed instruments
	 * @throws CommandException - exception in case of failure
	 */
	public List<Quote> getQuotes(String instruments) throws CommandException{
		List<Quote> quotes = new ArrayList<>();
		this.validateToken();
		if(StringUtils.isEmpty(instruments)) {
			throw new CommandException("Invalid input");
		}
		if(instruments.split(",").length<=1) {
			throw new CommandException("Please provide instruments by separating them with commas");
		}

		HttpGet httpGet = this.buildGetHeader(String.format(QUOTE_URL, instruments));
		JsonObject jsonObject = this.executeCommand(httpGet);
		
		Iterator<JsonElement> elementI = jsonObject.get("d").getAsJsonArray().iterator();
		while(elementI.hasNext()) {
			JsonElement next = elementI.next();
			if(next.getAsJsonObject().get("s").getAsString().equals("ok"))
				quotes.add(gson.fromJson(next.getAsJsonObject().get("v"), Quote.class));
		}
		return quotes;
	}
	
	/**
	 * Get the market depth
	 * @param instrument - instrument id
	 * @param includeOhlc - set to 1 to get open, high, low, closing and volume quantity
	 * @return {@link MarketDepth} of the passed instrument
	 * @throws CommandException - exception in case of failure
	 */
	public MarketDepth getMarketDepth(String instrument, int includeOhlc) throws CommandException {
		this.validateToken();
		if(StringUtils.isEmpty(instrument))
			throw new CommandException("instrument not provided");
		includeOhlc = includeOhlc==1 ? includeOhlc : 0;
		HttpGet httpGet = this.buildGetHeader(String.format(MARKET_DEPTH_URL, instrument, includeOhlc));
		JsonObject jsonObject = this.executeCommand(httpGet);

		if(jsonObject.get("s").getAsString().equals("ok"))
			return gson.fromJson(jsonObject.get("d").getAsJsonObject().get(instrument), MarketDepth.class) ;
		return gson.fromJson("", MarketDepth.class) ;
	}
	
	/**
	 * Method to send request to generate the TPIN - WARNING - NOT TESTED
	 * @return - true if the request was sent successfully
	 * @throws CommandException - exception in case of failure
	 */
	public boolean sendTPINRequest() throws CommandException {
		this.validateToken();
		HttpGet httpGet = this.buildGetHeader(EDIS_TPIN_GENERATE_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);		
		return jsonObject.get("s").getAsString().equals("ok") && jsonObject.get("code").getAsInt()==200;
	}
	
	/**
	 * Method to provide the necessary information regarding the holdings and status of holding
	 * @return - {@link EdisDetail} holding details
	 * @throws CommandException - exception in case of failure
	 */
	public List<EdisDetail> getEdisDetails() throws CommandException {
		List<EdisDetail> details = new ArrayList<>();
		this.validateToken();		
		HttpGet httpGet = this.buildGetHeader(EDIS_DETAILS_URL);
		JsonObject jsonObject = this.executeCommand(httpGet);

		if(jsonObject.get("s").getAsString().equals("ok")) {
			Iterator<JsonElement> iterator = jsonObject.get("data").getAsJsonArray().iterator();
			while(iterator.hasNext()) {
				JsonElement next = iterator.next();
				details.add(gson.fromJson(next.getAsJsonObject(), EdisDetail.class));
			}	
		} else {
			throw new CommandException(jsonObject.get("message").getAsString());
		}
		return details;
	}
	
	/**
	 * Method to get CDSL login page to submit holding information - WARNING - NOT TESTED 
	 * @param recordList - {@link EdisRecords}
	 * @return - CDSL login page
	 * @throws CommandException - exception in case of failure
	 */
	public String getEdisIndex(EdisRecords recordList) throws CommandException {
		this.validateToken();
		if(recordList==null || recordList.getRecordLst()==null || recordList.getRecordLst().length==0) {
			throw new CommandException("Invalid input");
		}
		
		JsonObject resp = this.executeCommandPost(EDIS_INDEX_URL, gson.toJson(recordList));
		if (resp.get("s").getAsString().equals("ok"))
			return resp.get("data").getAsString();
		else
			throw new CommandException("Response of the failed command: "+ resp.getAsString());
	}
	
	/**
	 * Method get the information/status of the provided transaction Id for the respective holdings - WARNING - NOT TESTED
	 * @param inquiry - 
	 * @return - {@link EdisInquiryData}
	 * @throws CommandException - exception in case of failure
	 */
	public EdisInquiryData getEdisInquiry(EdisInquiry inquiry) throws CommandException {
		this.validateToken();
		if(inquiry==null || StringUtils.isEmpty(inquiry.getTransactionId())) {
			throw new CommandException("Invalid input!");
		}
		JsonObject resp = this.executeCommandPost(EDIS_INQUIRY_URL, gson.toJson(inquiry));
		if (resp.get("s").getAsString().equals("ok"))
			return gson.fromJson(resp.get("data").getAsJsonObject(), EdisInquiryData.class);
		else
			throw new CommandException("Response of the failed command: "+ resp.getAsString());
	}
	
	//auth - oidc
	
	//pkce - oidc
	
	//auth - oauth2
	
	//pkce - oauth2
	
	
	//TODO - not sure if this method is needed, should be removed in future versions
	private boolean verifyTokenV1() throws CommandException {
		this.validateToken();
		if(!this.executeCommandOptions(VERIFY_TOKEN_URL, this.buildOptionsForTokenVerification(VERIFY_TOKEN_URL))) {
			throw new CommandException("Could not send HTTP options to the api endpoint");
		}
		JsonObject resp = this.executeCommandPost(VERIFY_TOKEN_URL, "", this.buildPostHeaderForTokenVerification(VERIFY_TOKEN_URL));
		return (resp.get("s").getAsString().equals("ok") && 
				resp.get("user_loggedin").getAsBoolean());
	}
	
	/**
	 * Internal method to validate the 'presence' of token
	 * @throws CommandException - exception in case of failure
	 */
	private void validateToken() throws CommandException {
		if (StringUtils.isEmpty(this.accessToken))
			throw new CommandException("Accesstoken is not generated.");

	}

	/**
	 * Build {@link HttpGet} for the given URL with the proper auth header
	 * @param URL - destination URL
	 * @return - HttpGet object
	 */
	private HttpGet buildGetHeader(String URL) {
		HttpGet httpGet = new HttpGet(URL);
		httpGet.addHeader("Authorization", APPID + ":" + this.accessToken);
		return httpGet;
	}

	/**
	 * Build {@link HttpPost} for the given URL with the proper auth header
	 * @param URL - destination URL
	 * @return - HttpPost obj
	 */
	private HttpPost buildPostHeader(String URL) {
		HttpPost httpPost = new HttpPost(URL);
		httpPost.addHeader("Authorization", APPID + ":" + this.accessToken);
		return httpPost;
	}

	/**
	 * Build {@link HttpPost} for the given URL to be used in Token verification end point.. currently its not being used
	 * @param URL - destination URL
	 * @return - HttpPost obj
	 */
	private HttpPost buildPostHeaderForTokenVerification(String URL) {
		HttpPost httpPost = new HttpPost(URL);
		httpPost.addHeader("Authorization", "{\"app_id\":\""+APPID+"\",\"access_token\":\"" + this.accessToken+"\"}");
		this.modifyHeaderForToken(httpPost, "POST");
		return httpPost;
	}

	/**
	 * Build {@link HttpOptions} for the given URL with the proper auth header
	 * @param URL - destination URL
	 * @return - HttpOptions obj
	 */
	private HttpOptions buildOptionsForTokenVerification(String URL) {
		HttpOptions options = new HttpOptions(URL);
		options.addHeader("Authorization", "{\"app_id\":\""+APPID+"\",\"access_token\":\"" + this.accessToken+"\"}");
		this.modifyHeaderForToken(options, "OPTIONS");
		return options;
	}
	
	/**
	 * Not in use
	 * @param httpPost - HttpPost
	 * @param method - method
	 */
	private void modifyHeaderForToken(HttpMessage httpPost, String method) {
		httpPost.addHeader(":authority", "api.fyers.in");
		httpPost.addHeader(":method", method);
		httpPost.addHeader(":path", "/vagator/v1/verify_token");
		httpPost.addHeader(":scheme", "https");
	}

	/**
	 * Build {@link HttpPut} for the given URL with auth header
	 * @param URL - Destination URL
	 * @return - HttpPut object
	 */
	private HttpPut buildPutHeader(String URL) {
		HttpPut httpPut = new HttpPut(URL);
		httpPut.addHeader("Authorization", APPID + ":" + this.accessToken);
		return httpPut;
	}

	/**
	 * Build {@link HttpDeleteWithBody} that extends {@link HttpDelete} to supply body in the delete command
	 * @param URL
	 * @return - HttpDeleteWithBody obj
	 */
	private HttpDeleteWithBody buildDeleteHeader(String URL) {
		HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(URL);
		httpDelete.addHeader("Authorization", APPID + ":" + this.accessToken);
		return httpDelete;
	}

	/**
	 * Execute the http get command
	 * @param httpGet - object to pass
	 * @return - response JSON Object
	 * @throws CommandException - exception in case of failure
	 */
	private JsonObject executeCommand(HttpGet httpGet) throws CommandException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(httpGet);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			client.close(); 
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			JsonObject jsonObject = jsonParser.parse(sb.toString()).getAsJsonObject();
			if (jsonObject.get("s").getAsString().equals("error"))
				throw new CommandException(jsonObject.get("message").getAsString());
			return jsonObject;

		} catch (IOException e) {
			throw new CommandException(e.getMessage());
		}
	}

	/**
	 * Execute http post command
	 * @param URL - destination url
	 * @param jsonPayload - payload in the body
	 * @return - response JSON Object
	 * @throws CommandException - exception in case of failure
	 */
	private JsonObject executeCommandPost(String URL, String jsonPayload) throws CommandException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = buildPostHeader(URL);
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity params;
		try {
			params = new StringEntity(jsonPayload);

		} catch (UnsupportedEncodingException e) {
			throw new CommandException(e.getMessage());
		}
		httpPost.setEntity(params);
		try {
			CloseableHttpResponse response = client.execute(httpPost);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			client.close();
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			JsonObject jsonObject = jsonParser.parse(sb.toString()).getAsJsonObject();
			if (jsonObject.get("s").getAsString().equals("error"))
				throw new CommandException(jsonObject.get("message").getAsString());
			return jsonObject;
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	/**
	 * Execute http post for given url with payload via http post
	 * @param URL - destination URL
	 * @param jsonPayload - payloaf to the command
	 * @param httpPost - http post obj to be used
	 * @return - response JSON Object
	 * @throws CommandException - exception in case of failure
	 */
	private JsonObject executeCommandPost(String URL, String jsonPayload, HttpPost httpPost) throws CommandException {
		CloseableHttpClient client = HttpClients.createDefault();
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity params;

		if(jsonPayload.length()>0) {
			try {
				params = new StringEntity(jsonPayload);
	
			} catch (UnsupportedEncodingException e) {
				throw new CommandException(e.getMessage());
			}
			httpPost.setEntity(params);
		}	

		try {
			CloseableHttpResponse response = client.execute(httpPost);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			client.close();
			JsonObject jsonObject = jsonParser.parse(sb.toString()).getAsJsonObject();
			if (jsonObject.get("s").getAsString().equals("error"))
				throw new CommandException(jsonObject.get("message").getAsString());
			return jsonObject;
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	/**
	 * Send Http Put command to the given URL
	 * @param URL - destination URL
	 * @param jsonPayload - payload to the command
	 * @return - response JSON Object
	 * @throws CommandException - exception in case of failure
	 */
	private JsonObject executeCommandPut(String URL, String jsonPayload) throws CommandException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut httpPut = buildPutHeader(URL);
		httpPut.addHeader("Content-Type", "application/json");
		StringEntity params;
		try {
			params = new StringEntity(jsonPayload);
		} catch (UnsupportedEncodingException e) {
			throw new CommandException(e.getMessage());
		}
		httpPut.setEntity(params);
		try {
			CloseableHttpResponse response = client.execute(httpPut);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			client.close();
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			JsonObject jsonObject = jsonParser.parse(sb.toString()).getAsJsonObject();
			if (jsonObject.get("s").getAsString().equals("error"))
				throw new CommandException(jsonObject.get("message").getAsString());
			return jsonObject;
			
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	/**
	 * This is to send the http delete command to close the position
	 * 
	 * @param URL - destination URL
	 * @param jsonPayload - payload to the body
	 * @throws CommandException - exception in case of failure
	 */
	private JsonObject executeCommandDelete(String URL, String jsonPayload) throws CommandException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpDeleteWithBody httpDelete = buildDeleteHeader(URL);
		httpDelete.addHeader("Content-Type", "application/json");
		StringEntity params;
		try {
			params = new StringEntity(jsonPayload);
		} catch (UnsupportedEncodingException e) {
			throw new CommandException(e.getMessage());
		}
		httpDelete.setEntity(params);
		try {
			CloseableHttpResponse response = client.execute(httpDelete);
			InputStream is = response.getEntity().getContent();
			StringBuffer sb = new StringBuffer();
			byte[] allBytes = ByteStreams.toByteArray(is);
			is.close();
			client.close();
			sb.append(new String(allBytes, StandardCharsets.US_ASCII));
			JsonObject jsonObject = jsonParser.parse(sb.toString()).getAsJsonObject();
			if (jsonObject.get("s").getAsString().equals("error"))
				throw new CommandException(jsonObject.get("message").getAsString());
			
			return jsonObject;
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	
	/**
	 * Send Http Options request 
	 * @param URL - destination URL
	 * @param options - HttpOptions object
	 * @return - true if the response code is 200, false otherwise
	 * @throws CommandException - exception in case of failure
	 */
	private boolean executeCommandOptions(String URL, HttpOptions options) throws CommandException {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = client.execute(options);
			client.close();
			return (response.getStatusLine().getStatusCode()==200);
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	/**
	 * Set the access token
	 * @param accessToken - access token to be set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * Get order if from the symbol
	 * @param symbol - symbol to be looked into the map
	 * @return - orderid for the given symbol, empty string otherwise
	 */
	public String getOrderidForSymbol(String symbol) {
		return this.orderidMap.containsKey(symbol) ? this.orderidMap.get(symbol) : StringUtils.EMPTY; 
	}

	/**
	 * Invalidate access token.
	 * This MUST be changed if we are able to find an API that we can use to logout from the system.
	 */
	public void invalidateAccessToken() {
		this.accessToken = StringUtils.EMPTY;
	}
}
