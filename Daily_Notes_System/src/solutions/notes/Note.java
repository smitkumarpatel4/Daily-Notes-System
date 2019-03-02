package solutions.notes;

import java.util.Date;

public class Note {

	static int count = 0;
	
	String title;
	String text;
	Date created;
	int ownerId;
	int id;
	
	public Note(int ownerId, String title, String text) {
		this.id = count++;
		this.title = title;
		this.text = text;
		this.ownerId = ownerId;
		this.created = new Date();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public int getId() {
		return id;
	}
	
	
}
