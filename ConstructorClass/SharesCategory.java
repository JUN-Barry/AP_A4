package ConstructorClass;

public class SharesCategory {
	 private String category;
	 private int  amount;
	 
	public SharesCategory(String category, int amount) {
		super();
		this.setCategory(category);
		this.setAmount(amount);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


}
