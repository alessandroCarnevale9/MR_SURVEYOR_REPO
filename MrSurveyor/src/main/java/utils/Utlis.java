package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utlis {
	
	public enum InputType {
		USERMAME,
		NAME,
		EMAIL,
		PASSWORD;
	}
	
	private static final String USERNAME_REGEX = "^[A-Za-z0-9_-]+$";
	private static final String NAME_REGEX = "^[A-Za-z\\u00C0-\\u024F\\s'-]+$";
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
	private static final String PASSWD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_\\-.,:;?< >\\/()|*^?]).{8,}$";
	
	private static final String CONTEXT_PATH = "/MrSurveyor";
	
	public static boolean validate(String inputValue, InputType type) {
		
		if(inputValue == null || inputValue.trim().equals(""))
			return false;
		
		Pattern pattern;
		
		switch(type) {
			
			case USERMAME:
				pattern = Pattern.compile(USERNAME_REGEX);
				break;
			
			case NAME:
				pattern = Pattern.compile(NAME_REGEX);
				break;
				
			case EMAIL:
				pattern = Pattern.compile(EMAIL_REGEX);
				break;
				
			case PASSWORD:
				pattern = Pattern.compile(PASSWD_REGEX);
				break;
				
			default:
				return false;
		}
		
		Matcher matcher = pattern.matcher(inputValue);
		return matcher.matches();
	}
	
	public static String categoryLoop(String category, String subcategory) {
		
		String result = "";

		String subcategoryCpy = subcategory;

		/*
		 * if(((category+subcategory).length() > 20)) { subcategoryCpy =
		 * subcategory.substring(0, subcategory.length()-subcategory.length()+4);
		 * subcategoryCpy += "."; }
		 */

		result += (subcategory != null && !subcategory.trim().equals(""))
				? "<a href=\"" + CONTEXT_PATH + "/CatalogServlet?category=" + category + "\">" + category + "</a>" + ", "
						+ "<a href=\"" + CONTEXT_PATH + "/CatalogServlet?subcategory=" + subcategory + "\">"
						+ subcategoryCpy + "</a>"
				: "<a href=\"" + CONTEXT_PATH + "/CatalogServlet?category=" + category + "\">" + category + "</a>";

		return result;
	}
}
