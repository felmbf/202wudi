package util;

public class Food_Info {

	private String foodName;
	private String foodType;
	private String restName;
	private int salesCount;
	private double foodPrice;
	
	public Food_Info(String foodName, String foodType, String restName,
			int salesCount, double foodPrice) {
		super();
		this.foodName = foodName;
		this.foodType = foodType;
		this.restName = restName;
		this.salesCount = salesCount;
		this.foodPrice = foodPrice;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	@Override
	public String toString() {
		return "Food_Info [foodName=" + foodName + ", foodType=" + foodType
				+ ", restName=" + restName + ", salesCount=" + salesCount
				+ ", foodPrice=" + foodPrice + "]";
	}
	
	
}
