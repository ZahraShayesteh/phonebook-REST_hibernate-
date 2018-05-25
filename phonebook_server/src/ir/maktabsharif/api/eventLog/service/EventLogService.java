package ir.maktabsharif.api.eventLog.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ir.maktabsharif.api.eventLog.dto.EventLogFullDTO;
import ir.maktabsharif.model.entity.EventLog;
import ir.maktabsharif.model.manager.EventLogManager;

@Path("/event")
public class EventLogService {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(EventLogFullDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@DELETE
	public Response remove(EventLogFullDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(EventLogFullDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventLogFullDTO> getAll() {
		List<EventLog> e= new ArrayList<EventLog>();
		e=EventLogManager.getInstance().list();
		List<EventLogFullDTO> dtos=new ArrayList<>();
		for(int i=0;i<e.size();i++){
			EventLogFullDTO efdto=new EventLogFullDTO();
			dtos.add(efdto.convertToDto(e.get(i)));
		}
		return dtos;
	}
}