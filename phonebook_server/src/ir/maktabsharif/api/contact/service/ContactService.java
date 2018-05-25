package ir.maktabsharif.api.contact.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response.Status;

import ir.maktabsharif.api.contact.dto.ContactFullDTO;
import ir.maktabsharif.api.contact.dto.ContactLiteDTO;
import ir.maktabsharif.model.dao.ContactDAO;
import ir.maktabsharif.model.entity.Contact;
import ir.maktabsharif.model.manager.ContactManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("contact")
public class ContactService{
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ContactFullDTO e) {
		Contact c=e.convertToObject();
		try{
			if(ContactManager.getInstance().add(c)){//if inserting was successful and if condition was true
				return Response.status(Status.NO_CONTENT).build();//204:The server has fulfilled the request but does not need to return an entity-body, and might want to return updated metainformation.
			}
			return Response.status(Status.NOT_ACCEPTABLE).build();//if inserting was not successful and if condition was false
		}
		catch(Exception x){
			return Response.status(Status.NOT_ACCEPTABLE).build();//406:The requested resource is capable of generating only content not acceptable according to the Accept headers sent in the request.
		}
	}


	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(ContactFullDTO e) {
		Contact c=e.convertToObject();
		try{
			if(ContactManager.getInstance().delete(c)){//if deleting was successful and if condition was true
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.NOT_ACCEPTABLE).build();//if deleting was not successful and if condition was false
		}
		catch(Exception x){
			return Response.status(Status.NOT_ACCEPTABLE).build();//if condition in if condition throw an exception
		}
	}


	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") Integer id) {
		try{
			if(ContactManager.getInstance().delete(ContactDAO.getInstance().getById(id))){//if deleting was successful and if condition was true
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.NOT_ACCEPTABLE).build();//if deleting was not successful and if condition was false
		}
		catch(Exception x){
			return Response.status(Status.NOT_ACCEPTABLE).build();//if condition in if condition throw an exception
		}
	}


	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ContactFullDTO e) {
		Contact c=e.convertToObject();
		try{
			if(ContactManager.getInstance().update(c)){//if updating was successful and if condition was true
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.NOT_ACCEPTABLE).build();//if updating was not successful and if condition was false
		}
		catch(Exception x){
			return Response.status(Status.NOT_ACCEPTABLE).build();//if condition in if condition throw an exception
		}
	}


	@GET
	@Path("/getAll/fullContact")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContactFullDTO> getAllFull() {
		List<Contact> c= new ArrayList<Contact>();
		c=ContactManager.getInstance().list();
		List<ContactFullDTO> dtos=new ArrayList<>();
		for(int i=0;i<c.size();i++){
			ContactFullDTO cfdto=new ContactFullDTO();
			dtos.add(cfdto.convertToDto(c.get(i)));
		}
		return dtos;
	}


	@GET
	@Path("/getAll/liteContact")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContactLiteDTO> getAllLite() {
		List<Contact> c= new ArrayList<Contact>();
		c=ContactManager.getInstance().list();
		List<ContactLiteDTO> dtos=new ArrayList<>();
		for(int i=0;i<c.size();i++){
			ContactLiteDTO cfdto=new ContactLiteDTO();
			dtos.add(cfdto.convertToDto(c.get(i)));
		}
		return dtos;
	}

}
