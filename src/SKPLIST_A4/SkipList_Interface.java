package SKPLIST_A4;

public interface SkipList_Interface {
	public boolean insert(double value);
	public boolean remove(double value);
	public boolean contains(double value);
	public double findMin();
	public double findMax();
	public int size();
	public boolean empty();
	public void clear();
	public int level();
	public int max();
	public SkipList_Node getRoot();
	public void setSeed(long seed);
	public void setProbability(double probability);
}
