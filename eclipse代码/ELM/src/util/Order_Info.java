package util;

public class Order_Info {

	private String O_user;
	private String O_restName;
	private String O_food;
	private String O_date;
	private double O_totalPrice;
	
	public Order_Info(String o_user, String o_restName, String o_food,
			String o_date, double o_totalPrice) {
		super();
		O_user = o_user;
		O_restName = o_restName;
		O_food = o_food;
		O_date = o_date;
		O_totalPrice = o_totalPrice;
	}

	public String getO_user() {
		return O_user;
	}

	public void setO_user(String o_user) {
		O_user = o_user;
	}

	public String getO_restName() {
		return O_restName;
	}

	public void setO_restName(String o_restName) {
		O_restName = o_restName;
	}

	public String getO_food() {
		return O_food;
	}

	public void setO_food(String o_food) {
		O_food = o_food;
	}

	public String getO_date() {
		return O_date;
	}

	public void setO_date(String o_date) {
		O_date = o_date;
	}

	public double getO_totalPrice() {
		return O_totalPrice;
	}

	public void setO_totalPrice(double o_totalPrice) {
		O_totalPrice = o_totalPrice;
	}

	@Override
	public String toString() {
		return "Order_Info [O_user=" + O_user + ", O_restName=" + O_restName
				+ ", O_food=" + O_food + ", O_date=" + O_date
				+ ", O_totalPrice=" + O_totalPrice + "]";
	}
	
}
