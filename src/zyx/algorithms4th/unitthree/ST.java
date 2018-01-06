package zyx.algorithms4th.unitthree;

public interface ST<Key extends Comparable<Key>, Value> extends Iterable<Key>{

	Value get(Key key);
	
	void put(Key key, Value value);
	
	void delete(Key key);
	
	boolean contains(Key key);
	
	boolean isEmpty();
	
	int size();
	
	int rank(Key min, Key max);
	
	Key select(int k);
	
	Iterable<Key> keys();
	
	Key max();
	
	Key min();
	
	Key floor(Key key);
	
	Key ceiling(Key key);
	
	void deleteMax();
	
	void deleteMin();
}
