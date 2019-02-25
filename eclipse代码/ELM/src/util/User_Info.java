package util;

public class User_Info {

	private String username;
	private String password;
	private String telnum;
	private double yuE;
	
	public User_Info(String username, String password, String telnum, double yuE) {
		super();
		this.username = username;
		this.password = password;
		this.telnum = telnum;
		this.yuE = yuE;
	}

	@Override
	public String toString() {
		return "User_Info [username=" + username + ", password=" + password
				+ ", telnum=" + telnum + ", yuE=" + yuE + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public double getYuE() {
		return yuE;
	}

	public void setYuE(double yuE) {
		this.yuE = yuE;
	}
	
}
