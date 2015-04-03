package models;

public class Gem implements Comparable<Gem> {

	private long id;
	private String name;
	private String description;
	private double price;
	private int shine;
	private int rarity;
	private int faces;

	public Gem() {
	}

	public Gem(long id, String name, String description, double price,
			int shine, int rarity, int faces) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.shine = shine;
		this.rarity = rarity;
		this.faces = faces;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getShine() {
		return shine;
	}

	public void setShine(int shine) {
		this.shine = shine;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public int getFaces() {
		return faces;
	}

	public void setFaces(int faces) {
		this.faces = faces;
	}

	@Override
	public String toString() {
		return "(" + this.id + ", " + this.name + ", " + this.description + ", " + this.price
				+ ", " + this.shine + ", " + this.rarity + ", " + this.faces + ")";
	}

	@Override
	public int compareTo(Gem G) {
		return this.name.compareToIgnoreCase(G.getName());
	}

}
