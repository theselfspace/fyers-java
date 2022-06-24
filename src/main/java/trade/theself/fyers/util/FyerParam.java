package trade.theself.fyers.util;

/**
 * 
 * @author theselfspace
 *
 */
public class FyerParam {

	public static final int ORDER_TYPE_LIMIT = 1;
	public static final int ORDER_TYPE_MARKET = 2;
	public static final int ORDER_TYPE_STOP_MARKET = 3;
	public static final int ORDER_TYPE_STOP_LIMIT = 4;
	
	public static final int ORDER_SIDE_BUY = 1;
	public static final int ORDER_SIDE_SELL = -1;
	
	public static final String ORDER_PRODUCTTYPE_CNC = "CNC";
	public static final String ORDER_PRODUCTTYPE_INTRADAY = "INTRADAY";
	public static final String ORDER_PRODUCTTYPE_MARGIN = "MARGIN";
	public static final String ORDER_PRODUCTTYPE_CO = "CO";
	public static final String ORDER_PRODUCTTYPE_BO = "BO";
	
	public static final String ORDER_VALIDITY_IOC = "IOC";
	public static final String ORDER_VALIDITY_DAY = "DAY";
	
	
	public static final int RESPONSE_STATUS_CANCELLED = 1;
	public static final int RESPONSE_STATUS_FILLED = 2;
	public static final int RESPONSE_STATUS_UNUSED = 3;
	public static final int RESPONSE_STATUS_TRANSIT = 4;
	public static final int RESPONSE_STATUS_REJECTED = 5;
	public static final int RESPONSE_STATUS_PENDING = 6;
	public static final int RESPONSE_STATUS_EXPIRED = 7;
	
	public static final int RESPONSE_SEGMENT_EQUITY = 10;
	public static final int RESPONSE_SEGMENT_FNO = 11;
	public static final int RESPONSE_SEGMENT_CURRENCY = 12;
	public static final int RESPONSE_SEGMENT_COMMODITY = 20;
	
	public static final String CANDLE_RES_DAY = "D";
	public static final String CANDLE_RES_1M = "1";
	public static final String CANDLE_RES_2M = "2";
	public static final String CANDLE_RES_3M = "3";
	public static final String CANDLE_RES_5M = "5";
	public static final String CANDLE_RES_10M = "10";
	public static final String CANDLE_RES_15M = "15";
	public static final String CANDLE_RES_20M = "20";
	public static final String CANDLE_RES_30M = "30";
	public static final String CANDLE_RES_60M = "60";
	public static final String CANDLE_RES_120M = "120";
	public static final String CANDLE_RES_240M = "240";
	
	public static final int CANDLE_DATE_FORMAT_EPOCH = 0;
	public static final int CANDLE_DATE_FORMAT_STR = 1;
	
	public static final int MULTI_ORDER_LIMIT = 10;
	
}
