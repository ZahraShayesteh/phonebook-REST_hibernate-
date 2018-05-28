package ir.maktabsharif.api.role.service;

import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.Response.Status;

import ir.maktabsharif.api.role.dto.RoleFullDTO;
import ir.maktabsharif.model.entity.Role;
import ir.maktabsharif.model.manager.RoleManager;

@Path("role")
public class RoleService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(RoleFullDTO e) {
		try{
			if (RoleManager.getInstance().add(e.convertToObject())) {
				return Response.status(Status.OK).entity("Role added successfully!").build();
			} else {
				return Response.status(Status.NOT_ACCEPTABLE).entity("Not acceptable content!").build();
			}
		}catch(Exception x)
		{
			return Response.status(Status.NOT_ACCEPTABLE).entity(x.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{roleName}")
	public Response remove(@PathParam("roleName") String roleName) {//not defined in context of problem(question) so it will return method not allowed
		return Response.status(Status.METHOD_NOT_ALLOWED).entity("Delete not allowed for role!").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(RoleFullDTO e) {//not defined in context of problem(question) so it will return method not allowed
		try{
			if (RoleManager.getInstance().update(e.convertToObject())) {
				return Response.status(Status.OK).entity("Role updated successfully!").build();
			} else {
				return Response.status(Status.METHOD_NOT_ALLOWED).entity("Action not allowed!").build();
			}
		}catch(Exception x)
		{
			return Response.status(Status.NOT_ACCEPTABLE).entity(x.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleFullDTO> getAll() {		
		List<Role> r= new ArrayList<Role>();
		r=RoleManager.getInstance().list();
		List<RoleFullDTO> dtos=new ArrayList<>();
		for(int i=0;i<r.size();i++){
			RoleFullDTO rfdto=new RoleFullDTO();
			dtos.add(rfdto.convertToDto(r.get(i)));
		}
		return dtos;
	}

	@GET
	@Path("/{roleName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoleByName(@PathParam("roleName") String roleName) {
		RoleFullDTO rdto=new RoleFullDTO();
		Role role = RoleManager.getInstance().getByName(roleName);
		rdto.convertToDto(role);
		if (rdto.getName() != null && rdto.getFeatures()!=null) {
			return Response.ok(rdto).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("Role not found!").build();
		}
	}
}
