package models;

public class Review {

	private long id;
	private String title;
	private long date;
	private String author;
	private String text;

	public Review() {
	}

	public Review(long id, String title, long date, String author, String text) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.author = author;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "(" + this.id + ", " + this.title + ", " + this.date + ", " + this.author
				+ ", " + this.text + ")";
	}

}
