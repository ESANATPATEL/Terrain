package main.capone;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;

import main.capone.pojo.Product;
import main.capone.pojo.Products;
import main.capone.pojo.Token;

public class CaponeConnectionTest {
	Token t=null;
	public static void main(String[] args) {	
		  
		CaponeConnectionTest test = new CaponeConnectionTest();
		test.getAccessToken(Token.class);
		
		  }
		
	public void callAPI(String token) {
		try {
			System.out.println("Sending 'GET' request");
	    	String url = "https://api-sandbox.capitalone.com/credit-offers/products/cards/consumer";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json; v=3");
			con.setRequestProperty("Content-Type", "application/json");
			//String payload= "{\"client_id\":\"03298a11a5464a2abab1ac26f0a74e87\",\"client_secret\":\"97b1ce3ce47ba8434366cdfb10e51f74\",\"grant_type\":\"client_credentials\"}";
			
			//String payload= "client_id=03298a11a5464a2abab1ac26f0a74e87&client_secret=97b1ce3ce47ba8434366cdfb10e51f74&grant_type=client_credentials";
			con.setRequestProperty("Accept-Language", "en-US");
			String auth = "Bearer "+token;// + ", Accept-Language: en-US";
			System.out.println("auth::::::="+auth);
			con.setRequestProperty("Authorization", auth);
			
			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
/*			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(payload);
			wr.flush();
			wr.close();*/

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			//System.out.println("Post parameters : " + payload);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println("tostring="+response.toString());
			
			
			Products t1 = (Products) new Gson().fromJson(response.toString(),Products.class);
			 
			System.out.println("getProductCount="+t1.getProductCount());
			System.out.println("getProductCount values="+t1.getProducts().size());
			List<Product> prodList =t1.getProducts();
			for (int i=0; i < prodList.size(); i++) {
				Product prd=prodList.get(i);
				System.out.println("item desc="+prd.getProductDisplayName());

			}
			
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
		
		
	}
private void getAccessToken(Class<? extends Token> responseClazz) {
	  try {
	    	String url = "https://api-sandbox.capitalone.com/oauth2/token";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", USER_AGENT);
			//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//String payload= "{\"client_id\":\"03298a11a5464a2abab1ac26f0a74e87\",\"client_secret\":\"97b1ce3ce47ba8434366cdfb10e51f74\",\"grant_type\":\"client_credentials\"}";
			
			String payload= "client_id=03298a11a5464a2abab1ac26f0a74e87&client_secret=97b1ce3ce47ba8434366cdfb10e51f74&grant_type=client_credentials";

			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(payload);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + payload);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println("tostring="+response.toString());
			 t = (Token) new Gson().fromJson(response.toString(),responseClazz);

			System.out.println("value="+t.getAccess_token());
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	  callAPI(t.getAccess_token());
	  }
}