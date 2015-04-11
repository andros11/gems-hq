package models;

import adt.SortedCircularDoublyLinkedList;


public class GemList {

	private SortedCircularDoublyLinkedList<Gem> gemList;
	int counter = 1;

	private GemList() {
		this.gemList = new SortedCircularDoublyLinkedList<Gem>();
	}

	public Gem addGem(Gem obj) {
		long id = this.counter++;
		obj.setId(id);
		this.gemList.add(obj);
		return obj;
	}

	public Gem getGemById(long id) {
		for (Gem G : this.gemList) {
			if (G.getId() == id) {
				return G;
			}
		}
		return null;
	}

	public Gem[] getAllGems() {
		Gem result[] = new Gem[this.gemList.size()];
		for (int i = 0; i < this.gemList.size(); ++i) {
			result[i] = this.gemList.get(i);
		}
		return result;
	}

	public boolean deleteGem(long id) {
		int target = -1;

		for (int i = 0; i < this.gemList.size(); ++i) {
			if (this.gemList.get(i).getId() == id) {
				target = i;
				break;
			}
		}
		if (target == -1) {
			return false;
		}
		else {
			return this.gemList.remove(target);
		}
	}

	public Gem updateGem(Gem obj) {
		int target = -1;

		for (int i = 0; i < this.gemList.size(); ++i) {
			if (this.gemList.get(i).getId() == obj.getId()) {
				target = i;
				break;
			}
		}
		if (target == -1) {
			return null;
		}
		else {
			Gem G = this.gemList.get(target);
			G.setName(obj.getName());
			G.setDescription(obj.getDescription());
			G.setPrice(obj.getPrice());
			G.setRarity(obj.getRarity());
			G.setShine(obj.getShine());
			G.setFaces(obj.getFaces());
			G.setColor(obj.getColor());
			return G;
		}
	}

	private static GemList singleton = new GemList();

	public static GemList getInstance() {
		return singleton;
	}
}
