package org.songzx.restruction.model;

import java.util.List;

import org.songzx.restruction.dao.ShopMapper;
import org.songzx.restruction.util.OutInfo;

public class SaleShopOnePort extends SaleShop {

	private List<Integer> portList;

	public SaleShopOnePort(List<Integer> portList) {
		this.portList = portList;
	}

	@Override
	public void execute(String userName, long price, Integer port) {
		if (portList == null || portList.size() <= 0) {
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
				ShopDO nShopDO = shopMapper.getShopByShopId(shopDO.getId());
				// 信息输出
				long giveChance = price - nShopDO.getShopPrice();
				String yNumbers = shopDO.getNumber().toString();
				String nNumbers = nShopDO.getNumber().toString();
				OutInfo.outInfo(userName, price, port.toString(), shopDO.getShopName(),
						shopDO.getShopPrice().toString(), giveChance, yNumbers, nNumbers);
			}
		} else {
			getSaleShop().execute(userName, price, port);
		}
	}
}
