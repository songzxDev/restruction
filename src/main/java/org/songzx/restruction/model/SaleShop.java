package org.songzx.restruction.model;

public abstract class SaleShop {
	private SaleShop saleShop;

	public SaleShop getSaleShop() {
		return saleShop;
	}

	public void setSaleShop(SaleShop saleShop) {
		this.saleShop = saleShop;
	}

	public abstract void execute(String userName, long price, Integer port);
}
