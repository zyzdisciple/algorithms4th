package zyx.algorithms4th.unitfive;

public class User {

	private int group;
	
	private String name;
	
	private String sGroup;
	
	public User(String name, int group) {
		
		this.name = name;
		this.group = group;
	}
	
	public User(String name, String sGroup) {
		
		this.sGroup = sGroup;
		this.name = name;
	}

	public int getGroup() {
		return group;
	}

	public String getName() {
		return name;
	}
	
	public String getsGroup() {
		return sGroup;
	}

	@Override
	public String toString() {
		
		return "sGroup: " + sGroup + " name:" + name;
	}
}
