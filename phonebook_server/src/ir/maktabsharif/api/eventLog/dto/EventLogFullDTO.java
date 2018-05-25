package ir.maktabsharif.api.eventLog.dto;

import java.util.Date;

import ir.maktabsharif.model.entity.EventLog;

public class EventLogFullDTO {
	private long id;
	private String name;
	private String description;
	private Date time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public EventLogFullDTO() {
	}

	public EventLogFullDTO(long id, String name, String description, Date time) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
	}

	public EventLogFullDTO convertToDto(EventLog eventLog) {
		if(eventLog!=null){
			this.id = eventLog.getId();
			this.name = eventLog.getName();
			this.description = eventLog.getDescription();
			this.time = eventLog.getTime();
			return this;
		}
		return null;
	}

	public EventLog convertToObject() {
		EventLog eventLog = new EventLog();
		eventLog.setId(this.getId());
		eventLog.setName(this.getName());
		eventLog.setDescription(this.getDescription());
		eventLog.setTime(this.getTime());
		return eventLog;
	}
}
