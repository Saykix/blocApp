package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import Class.employer;
import Class.service;
import Class.site;

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
	
	
	public static void employerPut(int id,  employer data) throws IOException{
		
		URL url = new URL("http://localhost:5187/api/Employer/"+id);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        String jsonInputString = AddEmployerJson(data);
        System.out.println(jsonInputString);
        // Sortie permettant d'écrire pour le post
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        // Entrée permettant de récupérer la réponse du serveur
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
	}
	public static void employerPost(employer data) throws IOException{
        AddEmployerJson(data);
        URL url = new URL("http://localhost:5187/api/Employer/");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        System.out.println(conn);

        String jsonInputString = AddEmployerJson(data);

        // Sortie permettant d'écrire pour le post
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        // Entrée permettant de récupérer la réponse du serveur
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
	}
	
	public static void employerDelete(int id) {
		try {
			url = new URL("http://localhost:5187/api/Employer/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("DELETE");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	public static JSONArray siteGetByVille(String ville) {
		try {
			url = new URL("http://localhost:5187/api/Site/site/"+ville);
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
	public static void siteDelete(int id) {
		try {
			url = new URL("http://localhost:5187/api/Site/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("DELETE");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	public static void sitePut(int id, site data) throws IOException{
		
        URL url = new URL("http://localhost:5187/api/Site/"+id);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        System.out.println(conn);

        String jsonInputString = AddSiteJson(data);

        // Sortie permettant d'écrire pour le post
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        // Entrée permettant de récupérer la réponse du serveur
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
	}
	
	public static void sitePost(site data) throws IOException{
        URL url = new URL("http://localhost:5187/api/Site/");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        System.out.println(conn);

        String jsonInputString = AddSiteJson(data);

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
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
	
	public static JSONArray serviceGetByName(String name) {
		try {
			url = new URL("http://localhost:5187/api/Service/service/"+name);
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

	public static void serviceDelete(int id) {
		try {
			url = new URL("http://localhost:5187/api/Service/"+id);
			try {
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("DELETE");
				
				String line = "";
				InputStreamReader input = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				StringBuilder response = new StringBuilder();
				while((line=reader.readLine()) != null) {
					response.append(line);
				}
				
				reader.close();
				
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in get method");
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	public static void servicePut(int id, String nomService) throws IOException{
		service lol = new service(nomService);
        AddClientJson(lol);
        URL url = new URL("http://localhost:5187/api/Service/"+id);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        System.out.println(conn);

        String jsonInputString = AddClientJson(lol);

        // Sortie permettant d'écrire pour le post
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        // Entrée permettant de récupérer la réponse du serveur
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
	}
	
	public static void servicePost(String nomService) throws IOException{
		service lol = new service(nomService);
        AddClientJson(lol);
        URL url = new URL("http://localhost:5187/api/Service/");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        System.out.println(conn);

        String jsonInputString = AddClientJson(lol);

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
	}
    public static String AddClientJson(service u) throws JsonProcessingException {
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CreateClient", theFilter);
        ObjectMapper om = new ObjectMapper();
        return om.writer(filters).writeValueAsString(u);
    }
    public static String AddEmployerJson(employer u) throws JsonProcessingException {
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("idEmployer");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CreateClient", theFilter);
        ObjectMapper om = new ObjectMapper();
        return om.writer(filters).writeValueAsString(u);
    }
    
    public static String AddSiteJson(site u) throws JsonProcessingException {
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("idEmployer");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CreateClient", theFilter);
        ObjectMapper om = new ObjectMapper();
        return om.writer(filters).writeValueAsString(u);
    }
	
	
}
