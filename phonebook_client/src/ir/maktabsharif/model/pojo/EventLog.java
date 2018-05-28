package ir.maktabsharif.model.pojo;

import java.util.Date;
public class EventLog {
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

	public EventLog() {
	}

	public EventLog(long id, String name, String description, Date time) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
	}

	@Override
	public String toString(){
		return "ID: "+getId()+", name: "+getName()+", description: "+getDescription()+"time: "+getTime();
	}
}
