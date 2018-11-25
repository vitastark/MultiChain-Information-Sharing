/**
 * @author Haiyang Sun
 * ClassName: Conditions
 * Description: This class combines all the properties of one record.
 * 		Users can create new object and set the member value.
 * 		Conditions object is the augment of Query.
 */
package MultichainTest;

public class Conditions {
	private String node = "";
	private String index = "";
	private String ref="";
	private String user="";
	private String activity="";
	private String resource="";
	private String lowerbound="";
	private String upperbound="";
	
	public Conditions() {}
	public Conditions(String node, String index, String ref, String user, String activity, String resource, String lowerbound, String upperbound) {
		this.node = node;
		this.index = index;
		this.ref = ref;
		this.user = user;
		this.activity = activity;
		this.resource = resource;
		this.upperbound = upperbound;
		this.lowerbound = lowerbound;
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getLowerbound() {
		return lowerbound;
	}
	public void setLowerbound(String lowerbound) {
		this.lowerbound = lowerbound;
	}
	public String getUpperbound() {
		return upperbound;
	}
	public void setUpperbound(String upperbound) {
		this.upperbound = upperbound;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public void showconds() {
		System.out.println("lowerbound: " + this.lowerbound);
		System.out.println("upperbound: " + this.upperbound);
		System.out.println("node: " + this.node);
		System.out.println("index: " + this.index);
		System.out.println("ref: " + this.ref);
		System.out.println("user: " + this.user);
		System.out.println("activity: " + this.activity);
		System.out.println("resource: " + this.resource);
	}
}
