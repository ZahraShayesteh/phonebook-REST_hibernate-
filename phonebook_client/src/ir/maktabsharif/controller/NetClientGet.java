package ir.maktabsharif.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ir.maktabsharif.model.pojo.Contact;
import ir.maktabsharif.model.pojo.ContactLiteDTO;
import ir.maktabsharif.model.pojo.Role;

public class NetClientGet {

	private  URL url;
	private  String Ip;
	private  String port;
	private  String path;
	private Role userRole;
	private Contact contact;
	private List<ContactLiteDTO> liteContactList;
	private List<Contact> fullContactList;

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

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<ContactLiteDTO> getLiteContactList() {
		return liteContactList;
	}

	public void setLiteContactList(List<ContactLiteDTO> liteContactList) {
		this.liteContactList = liteContactList;
	}

	public List<Contact> getFullContactList() {
		return fullContactList;
	}

	public void setFullContactList(List<Contact> fullContactList) {
		this.fullContactList = fullContactList;
	}

	public NetClientGet(String IP,String port,String path) throws MalformedURLException{
		setIp(IP);
		setPort(port);
		setPath(path);
		setUrl(new URL("http://"+getIp()+":"+getPort()+"/phonebook_server/"+getPath()));
		liteContactList=new ArrayList<ContactLiteDTO>();
	}

	// http://localhost:8080/RESTfulExample/json/product/get
	public String get(){
		try {

			HttpURLConnection conn = (HttpURLConnection) getUrl().openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			ObjectMapper mapper = new ObjectMapper();

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

				if(getPath().equals("contact/getAll/liteContact")){
					liteContactList=mapper.readValue(output, new TypeReference<List<ContactLiteDTO>>(){});
				}
				else if(getPath().startsWith("contact/")){
					contact = mapper.readValue(output, Contact.class);
				}
				else if(getPath().startsWith("contact/getAll/fullContact")){
					fullContactList=mapper.readValue(output, new TypeReference<List<Contact>>(){});
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
