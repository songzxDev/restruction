package org.songzx.restruction.domain;

import java.util.ArrayList;
import java.util.List;

import org.songzx.restruction.model.SaleShop;
import org.songzx.restruction.model.SaleShopListPort;
import org.songzx.restruction.model.SaleShopOnePort;

public class VendingMachine3 {
	public static void main(String[] args) {
		// 通过责任链模式实习在不改动接口参数的情况下，对多个端口传入参数的实现
		List<Integer> portList = new ArrayList<Integer>();
		portList.add(1);
		portList.add(2);

		SaleShop saleShopListPort = new SaleShopListPort(portList);
		SaleShop saleShopOnePort = new SaleShopOnePort(portList);

		saleShopListPort.setSaleShop(saleShopOnePort);
		String userName = "哈比";
		long price = 10L;
		int port = 2;
		saleShopListPort.execute(userName, price, port);
	}
}
