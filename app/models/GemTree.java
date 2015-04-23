package models;

import adt.bst.BSTImp;
import adt.bst.BinarySearchTree;
import adt.bst.LongComparator;


public class GemTree {

	private BinarySearchTree<Long, Gem> gemTree;
	int counter = 1;

	private GemTree() {
		LongComparator comp = new LongComparator();
		this.gemTree = new BSTImp<Long, Gem>(comp);
	}

	public Gem addGem(Gem obj) {
		long id = this.counter++;
		obj.setId(id);
		this.gemTree.add(id, obj);
		return obj;
	}

	public Gem getGemById(long id) {
		return this.gemTree.get(id);
	}

	public Gem[] getAllGems() {
		Gem result[] = new Gem[this.gemTree.size()];
		int i = 0;
		for (Gem G : gemTree) {
			result[i++] = G;
		}
		return result;
	}

	public boolean deleteGem(long id) {
		return this.gemTree.remove(id) != null;
	}

	public Gem updateGem(Gem obj) {
		Gem G = this.gemTree.get(obj.getId());
		// A gem exists by that id
		if (G != null) {
			G.setName(obj.getName());
			G.setDescription(obj.getDescription());
			G.setPrice(obj.getPrice());
			G.setRarity(obj.getRarity());
			G.setShine(obj.getShine());
			G.setFaces(obj.getFaces());
			G.setColor(obj.getColor());
		}
		return G;
	}

	private static GemTree singleton = new GemTree();

	public static GemTree getInstance() {
		return singleton;
	}
}
