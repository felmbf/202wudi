package util;

public class Rest_Info {

	private String restName;
	private String restIconUrl;
	private int salesCount;

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}


	public String getRestIconUrl() {
		return restIconUrl;
	}

	public void setRestIconUrl(String restIconUrl) {
		this.restIconUrl = restIconUrl;
	}
	
	

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	
	
	@Override
	public String toString() {
		return "Rest_Info [restName=" + restName + ", restIconUrl="
				+ restIconUrl + ", salesCount=" + salesCount + "]";
	}

	
	public Rest_Info(String restName, String restIconUrl, int salesCount) {
		super();
		this.restName = restName;
		this.restIconUrl = restIconUrl;
		this.salesCount = salesCount;
	}
	
}
