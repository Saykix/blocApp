package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

public class apiRequest {

	static URL url;
	public static JSONArray employerGet() {
		try {
			url = new URL("http://localhost:5187/api/Employer");
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in employerGetById method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray employerGetById(int id) {
		try {
			url = new URL("http://localhost:5187/api/Employer/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in employerGetById method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray siteGetById(int id) {
		try {
			url = new URL("http://localhost:5187/api/Site/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray siteGet() {
		try {
			url = new URL("http://localhost:5187/api/Site/");
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray serviceGet() {
		try {
			url = new URL("http://localhost:5187/api/Service/");
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray serviceGetById(int id) {
		try {
			url = new URL("http://localhost:5187/api/Service/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				JSONArray jsonArray = new JSONArray(response.toString());
				
				return jsonArray;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
				return null;
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	
}
