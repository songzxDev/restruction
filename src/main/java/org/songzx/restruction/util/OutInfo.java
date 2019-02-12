package org.songzx.restruction.util;

import org.songzx.restruction.dao.ShopMapper;
import org.songzx.restruction.model.ShopDO;

public class OutInfo {
	/**
	 * 输出日志
	 * 
	 * @param userName
	 * @param price
	 * @param port
	 * @param shopDO
	 */
	public static void log(String userName, long price, Integer port, ShopDO shopDO) {
		ShopMapper shopMapper = new ShopMapper();
		ShopDO nShopDO = shopMapper.getShopByShopId(shopDO.getId());
		// 信息输出
		long giveChance = price - nShopDO.getShopPrice();
		String yNumbers = shopDO.getNumber().toString();
		String nNumbers = nShopDO.getNumber().toString();
		outInfo(userName, price, port.toString(), shopDO.getShopName(), shopDO.getShopPrice().toString(), giveChance,
				yNumbers, nNumbers);
	}

	/**
	 * 信息输出
	 * 
	 * @param userName   用户名
	 * @param price      价格
	 * @param ports      出货口
	 * @param shopNames  商品名
	 * @param shopPrices 商品价格
	 * @param giveChance 找零
	 * @param yNumbers   原库存
	 * @param nNumbers   现在库存
	 */
	public static void outInfo(String userName, Long price, String ports, String shopNames, String shopPrices,
			long giveChance, String yNumbers, String nNumbers) {
		System.out.println("==================================================");
		System.out.println("购买用户：" + userName);
		System.out.println("投入金额：" + price + "元");
		System.out.println("出 货 口：" + ports);
		System.out.println("购买商品：" + shopNames);
		System.out.println("商品价格：" + shopPrices + "元");
		System.out.println("找    零：" + giveChance + "元");
		System.out.println("原 库 存：" + yNumbers);
		System.out.println("剩余库存：" + nNumbers);
		System.out.println("==================================================");
	}
}
