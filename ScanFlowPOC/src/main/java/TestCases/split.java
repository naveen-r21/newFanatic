package TestCases;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class split {

	public static void main(String[] args) {
		
		double amount =    1198.00;
		
		//long value =    Long.parseLong(amount);
		
//		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
//
//		 String moneyString = formatter.format(amount);
//
//		 System.out.println(moneyString);
		 
		 NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "IN"));
		 Currency currency = Currency.getInstance(new Locale("en", "IN"));
		 numberFormat.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		 
		 String moneyString = numberFormat.format(amount);
		 
		 System.out.println(moneyString);

	}

}
