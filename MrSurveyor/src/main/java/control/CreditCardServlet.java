package control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CreditCard;

@WebServlet("/CreditCardServlet")
public class CreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardNumber = request.getParameter("card_number");
		String cvc = request.getParameter("cvc");
		String expire = request.getParameter("expire");
		
		String DATE_REGEX = "^(0[1-9]|1[0-2])/\\d{2}$";
		
		if(cardNumber == null || cvc == null || expire == null) {
			request.setAttribute("errorCard", "Compila tutti i campi");
		} else {
			
			cardNumber = cardNumber.replace(" ", "");
			cardNumber = cardNumber.trim();
			
			cvc = cvc.replace(" ", "");
			cvc = cvc.trim();
			
			Pattern pattern = Pattern.compile(DATE_REGEX);
			Matcher matcher = pattern.matcher(expire);
				
			if(!matcher.matches()) {
				request.setAttribute("errorCard", "Data non valida");
			} else {
				CreditCard card = new CreditCard();
				card.setCardNumber(cardNumber);
				card.setCvc(cvc);
				

		        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");

		        try {
		        	
		            Date parsedDate = dateFormat.parse(expire);

		            Calendar calendar = Calendar.getInstance();
		            calendar.setTime(parsedDate);

		            Date resultDate = calendar.getTime();
		            
		            card.setExpirationDate(resultDate);
		            
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
				
		        try {
		        	if(card.isValidCreditCard()) {
			        	request.setAttribute("successCard", true);
			        	request.setAttribute("prevNumber", cardNumber);
			        	request.setAttribute("prevCVC", cvc);
			        	request.setAttribute("prevExp", expire);
			        }
		        }
		        catch(IllegalArgumentException e ) {
		        	request.setAttribute("errorCard", e.getMessage());
		        }
			}
		}

		request.getRequestDispatcher("/order_details.jsp").forward(request, response);
	}

}
