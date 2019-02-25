package util;

public class FoodType_Info {

	private String typeName;
	private String restName;
	
	public FoodType_Info(String typeName, String restName) {
		super();
		this.typeName = typeName;
		this.restName = restName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	
	@Override
	public String toString() {
		return "FoodType_Info [typeName=" + typeName + ", restName=" + restName
				+ "]";
	}
}
