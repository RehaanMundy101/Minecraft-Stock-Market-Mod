package com.teggo.stockmarketmod.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.ibm.icu.util.Calendar;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.Interval;

public class YahooAPI {
	//public Stock getStock(String stockName) throws IOException {
		//return YahooFinance.get(stockName);
	//}

	public static void main(String[] args) throws IOException {
		
		//YahooAPI yahooStockAPI = new YahooAPI();
		//System.out.println(yahooStockAPI.getStock("GOOG"));
		//Stock tesla = YahooFinance.get("GOOG", true);
		//System.out.println(tesla.getHistory());
		
		//Calendar from = Calendar.getInstance();
		//Calendar to = Calendar.getInstance();
		//from.add(Calendar.MONTH, 1);
		//System.out.println(YahooFinance.get("GOOG", Interval.DAILY));
		/*Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Scanner myObj = new Scanner(System.in);
	    System.out.println("stock");
	    String stock = myObj.nextLine();
	    System.out.println("period");
	    String period = myObj.nextLine();
	    System.out.println("interval");
	    String interval = myObj.nextLine();
	    System.out.println("length of period");
	    int length_of_period = myObj.nextInt();
		if (period == "Year") {
			from.add(Calendar.YEAR, length_of_period);	
		} else if (period == "Month") {
			from.add(Calendar.MONTH, length_of_period);
		}
		if (interval == "Monthly") {
			Stock stockdata = YahooFinance.get(stock, Interval.MONTHLY);	
		} else if (interval == "Weekly") {
			Stock stockdata = YahooFinance.get(stock, Interval.WEEKLY);
		} else if (interval == "Daily") {
			Stock stockdata = YahooFinance.get(stock, Interval.DAILY);			
		}
		String stockdatastr = stockdata 
		System.out.println(String());*/
		//Stock stock = YahooFinance.get("INTC");
		//stock.print();
		//Stock stock = YahooFinance.get("INTC");
		 
		//BigDecimal price = stock.getQuote().getPrice();
		//BigDecimal change = stock.getQuote().getChangeInPercent();
		//BigDecimal peg = stock.getStats().getPeg();
		//BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		 
		//stock.print();
		//Scanner myObj = new Scanner(System.in);
	    //System.out.println("stock");
	    //String stock1 = myObj.nextLine();
		//Stock stock = YahooFinance.get(stock1);
		//BigDecimal price = stock.getQuote(true).getPrice();
		//System.out.println(price);
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Scanner myObj = new Scanner(System.in);
	    System.out.println("stock");
	    String stock = myObj.nextLine();
	    System.out.println("period");
	    String period = myObj.nextLine();
	    System.out.println("interval");
	    String interval = myObj.nextLine();
	    System.out.println("length of period");
	    int length_of_period = myObj.nextInt();
		if (period == "Year") {
			from.add(Calendar.YEAR, (0 - length_of_period));	
		} else if (period == "Month") {
			from.add(Calendar.MONTH, (0 - length_of_period));
		}
		interval = "Monthly";
		if (interval == "Monthly") {
			System.out.println(YahooFinance.get(stock, Interval.MONTHLY));
		} else if (interval == "Weekly") {
			System.out.println(YahooFinance.get(stock, Interval.WEEKLY));
		} else if (interval == "Daily") {
			System.out.println(YahooFinance.get(stock, Interval.DAILY));
		}
		
		//from.add(Calendar.YEAR, -5); // from 5 years ago
		 
		//Stock google = YahooFinance.get("AAPL", Interval.WEEKLY);
		
	}
	
}

//viewdata AAPL Month 6 Daily