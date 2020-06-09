package SKPLIST_A4;

import java.util.Random;

public class SkipList implements SkipList_Interface {
	private SkipList_Node root;
	private final Random rand;
	private double probability;
	private static int MAXHEIGHT = 30;
	private int size;
	private int currentMax;

	public SkipList(int maxHeight) {
		this.root = new SkipList_Node(Double.NaN, maxHeight);
		this.rand = new Random();
		this.probability = 0.5;
		this.size = 0;
		this.currentMax = 1;
	}

	@Override
	public boolean insert(double value) {
		// TODO Auto-generated method stub
		if (contains(value)) {
			return false;
		}
		int level = leveling();
		SkipList_Node node = new SkipList_Node(value, level);
		SkipList_Node head = root;

		for (int i = currentMax - 1; i >= 0; i--) {
			while (null != head.getNext(i)) {
				if (head.getNext(i).getValue() > value) {
					break;
				}
				head = head.getNext(i);
			}
			if (i <= level) {
				node.setNext(i, head.getNext(i));
				head.setNext(i, node);
			}
		}
		size += 1;
		return true;
	}

	public int leveling() {
		int level = 0;
		for (int i = 0; i < MAXHEIGHT; i++) {
			if (flip()) {
				level++;
				if (level == this.currentMax) {
					currentMax += 1;
				}
			} else {
				break;
			}
		}
		return level;
	}

	@Override
	public boolean remove(double value) {
		// TODO Auto-generated method stub
		SkipList_Node head = root;
		boolean result = false;
		if (contains(value)) {
			size -= 1;
		}
		for (int i = currentMax - 1; i >= 0; i--) {
			while (null != head.getNext(i)) {
				if (head.getNext(i).getValue() > value) {
					break;
				}
				if (head.getNext(i).getValue() == value) {
					head.setNext(i, head.getNext(i).getNext(i));
					result = true;
					break;
				}
				head = head.getNext(i);
			}
		}
		return result;
	}

	@Override
	public boolean contains(double value) {
		// TODO Auto-generated method stub
		SkipList_Node head = root;
		for (int i = currentMax - 1; i >= 0; i--) {
			while (null != head.getNext(i)) {
				if (head.getNext(i).getValue() > value) {
					break;
				}
				if (head.getNext(i).getValue() == value) {
					return true;
				}
				head = head.getNext(i);
			}
		}
		return false;
	}

	@Override
	public double findMin() {
		// TODO Auto-generated method stub
		SkipList_Node head = root;
		double minimum = 500;
		for (int i = 0; i < 1; i++) {
			while (null != head.getNext(i)) {
				if (head.getNext(i).getValue() < minimum) {
					minimum = head.getNext(i).getValue();
				}
				head = head.getNext(i);
			}
		}
		return minimum;
	}

	@Override
	public double findMax() {
		// TODO Auto-generated method stub
		SkipList_Node head = root;
		double max = Double.MIN_VALUE;
		for (int i = currentMax - 1; i >= 0; i--) {
			while (null != head.getNext(i)) {
				if (head.getNext(i).getValue() > max) {
					max = head.getNext(i).getValue();
				}
				head = head.getNext(i);
			}
		}
		return max;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		new SkipList(MAXHEIGHT);
	}

	@Override
	public int level() {
		// TODO Auto-generated method stub
		if (empty()) {
			return -1;
		}
		return currentMax - 1;
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return MAXHEIGHT;
	}

	@Override
	public SkipList_Node getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void setSeed(long seed) {
		// TODO Auto-generated method stub
		rand.setSeed(seed);
	}

	@Override
	public void setProbability(double probability) {
		// TODO Auto-generated method stub
		this.probability = probability;
	}

	private boolean flip() {
		return rand.nextDouble() < probability;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		int levels;
		for(levels = 0; levels < root.getNext().length && root.getNext(levels) != null; levels++);

		StringBuilder[] sbs = new StringBuilder[levels];

		for(int i = 0; i < sbs.length; i++) {
			sbs[i] = new StringBuilder();
			sbs[i].append("level ").append(i).append(":");
		}

		SkipList_Node cur = root;

		while (cur.getNext(0) != null) {
			cur = cur.getNext(0);
			for(int i = levels - 1; i >= cur.getNext().length; i--) {
				sbs[i].append("\t");
			}
			for(int i = cur.getNext().length - 1; i >= 0; i--) {
				if (cur.getNext(i) == null) {
					levels --;
				}
				sbs[i].append("\t").append(cur.getValue());
			}
		}

		for(int i = sbs.length - 1; i >= 0; i --) {
			sb.append(sbs[i]).append("\n");
		}

		return sb.toString();
	}
}
