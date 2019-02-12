package org.songzx.restruction.domain;

import org.songzx.restruction.dao.ShopMapper;
import org.songzx.restruction.model.ShopDO;
import org.songzx.restruction.util.OutInfo;

public class VendingMachine2 {
	public static void main(String[] args) {
		String userName = "哈比";
		long price = 10L;
		int port = 2;
		vendingMachine(userName, price, port);
	}

	/**
	 * 得到商品
	 * 
	 * @param price 钱
	 * @param port  出货口
	 * @return
	 */
	public static void vendingMachine(String userName, long price, Integer port) {
		// 获取商品
		ShopMapper shopMapper = new ShopMapper();
		ShopDO shopDO = shopMapper.getShopByPort(port);
		// 判断价格 & 库存
		if (shopDO == null || shopDO.getId() == null || price < shopDO.getShopPrice() || shopDO.getNumber() <= 0) {
			System.out.println("该出货口暂无商品可销售");
			return;
		}
		// 扣减库存
		Boolean bool = shopMapper.deductionShopById(shopDO.getId());
		if (bool) {
			OutInfo.log(userName, price, port, shopDO);
		}
	}
}
