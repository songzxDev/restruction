package org.songzx.restruction.model;

import java.util.ArrayList;
import java.util.List;

import org.songzx.restruction.dao.ShopMapper;
import org.songzx.restruction.util.OutInfo;

public class StrategyListImpl implements Strategy {

	@Override
	public void vendingMachine(String userName, long price, List<Integer> portList) {
		ShopMapper shopMapper = new ShopMapper();
		String shopName = "", yNumbers = "", nNumbers = "", portListStr = "";
		Long totalPrice = 0L;
		List<ShopDO> shopDOLis = new ArrayList<ShopDO>();
		for (int port : portList) {
			// 获取商品
			ShopDO shopDO = shopMapper.getShopByPort(port);
			// 判断价格 & 库存
			if (shopDO == null || shopDO.getId() == null || shopDO.getNumber() <= 0) {
				System.out.println("【" + port + "】出货口暂无商品可销售");
				return;
			}
			shopName = shopDO.getShopName() + "," + shopName;
			totalPrice = totalPrice + shopDO.getShopPrice();
			shopDOLis.add(shopDO);
			portListStr = port + "," + portListStr;
		}

		// 判断总金额
		if (price < totalPrice) {
			System.out.println("金额错误");
			return;
		}
		// 执行扣除库存
		for (ShopDO shopDO : shopDOLis) {
			// 扣减库存
			Boolean bool = shopMapper.deductionShopById(shopDO.getId());
			if (bool) {
				ShopDO nShopDO = shopMapper.getShopByShopId(shopDO.getId());
				yNumbers = shopDO.getNumber() + "," + yNumbers;
				nNumbers = nShopDO.getNumber() + "," + nNumbers;
			}
		}
		Long giveChance = price - totalPrice;
		OutInfo.outInfo(userName, price, portListStr, shopName, totalPrice.toString(), giveChance, yNumbers, nNumbers);
	}

}
