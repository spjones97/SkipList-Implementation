package SKPLIST_A4;

public class SkipList_Node {
	private double value;
	private int level;
	private SkipList_Node[] next;
	
	public SkipList_Node(double value, int level) {
		this.next = new SkipList_Node[level + 1];
		this.value = value;
		this.level = level;
	}
	
	public void setNext(int height, SkipList_Node node) {
		next[height] = node;
	}
	
	public double getValue() {
		return value;
	}
	public SkipList_Node[] getNext() {
		return next;
	}
	public SkipList_Node getNext(int level) {
		return next[level];
	}
	public String toString() {
		return "" + value;
	}
}
