package org.songzx.restruction.model;

import java.util.List;

import org.songzx.restruction.dao.ShopMapper;
import org.songzx.restruction.util.OutInfo;

public class StrategyOneImpl implements Strategy {

	@Override
	public void vendingMachine(String userName, long price, List<Integer> portList) {
		if (portList != null && portList.size() == 1) {
			int port = portList.get(0).intValue();
			ShopMapper shopMapper = new ShopMapper();
			// 获取商品
			ShopDO shopDO = shopMapper.getShopByPort(port);
			// 判断价格 & 库存
			if (shopDO == null || price < shopDO.getShopPrice() || shopDO.getNumber() <= 0) {
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

}
