package ir.maktabsharif.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import ir.maktabsharif.model.pojo.Role;

public class NetClientPost {
	private  URL url;
	private  String Ip;
	private  String port;
	private  String path;
	private  Object obj;
	private Role userRole;

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public NetClientPost(String IP,String port,Object obj,String path) throws MalformedURLException{
		setIp(IP);
		setPort(port);
		setObj(obj);
		setPath(path);
		setUrl(new URL("http://"+getIp()+":"+getPort()+"/phonebook_server/"+getPath()));
	}

	public String post(){
		try{
			HttpURLConnection conn = (HttpURLConnection) getUrl().openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			ObjectMapper mapper = new ObjectMapper();

			// Convert object to JSON string
			String input = mapper.writeValueAsString(getObj());

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				String message= "Failed : HTTP error code : " + conn.getResponseCode()+ " , HTTP error message: "+conn.getResponseMessage();
				conn.disconnect();
				return message;
			}
			else{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output="";
				String line;
				while ((line = br.readLine()) != null) {
					output=output+line;
				}
				if(getObj().getClass().getName().equals("ir.maktabsharif.model.pojo.User") & getPath().equals("user/signin")){
					userRole = mapper.readValue(output, Role.class);
				}
				conn.disconnect();
				return null;
			}
		}catch(IOException e){
			return "Failed: invalid server IP or port!";
			//return e.getMessage() +" cause:"+ e.getCause();
		}
	}
}
