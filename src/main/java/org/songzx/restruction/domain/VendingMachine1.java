package org.songzx.restruction.domain;

import java.sql.ResultSet;

import org.songzx.restruction.model.ShopDO;
import org.songzx.restruction.util.DBHelper1;

public class VendingMachine1 {
	private static DBHelper1 dBHelper1 = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		vendingMachine("哈比", 10L, 2);
	}

	/**
	 * 得到商品
	 * 
	 * @param userName 用户
	 * @param price    钱
	 * @param port     出货口
	 * @return
	 */
	public static void vendingMachine(String userName, long price, int port) throws Exception {
		// 获取商品
		ShopDO shopDO = getShopByPort(port);
		// 判断价格 & 库存
		if (shopDO == null || price < shopDO.getShopPrice() || shopDO.getNumber() <= 0) {
			System.out.println("该出货口暂无商品可销售");
			return;
		}
		// 扣减库存
		deductionShopById(shopDO.getId());

		ShopDO shopDO1 = getShopByShopId(shopDO.getId());
		System.out.println("==================================================");
		System.out.println("购买用户：" + userName);
		System.out.println("投入金额：" + price + "元");
		System.out.println("出 货 口：" + port);
		System.out.println("购买商品：" + shopDO.getShopName());
		System.out.println("商品价格：" + shopDO.getShopPrice() + "元");
		System.out.println("找    零：" + (price - shopDO.getShopPrice()) + "元");
		System.out.println("原 库 存：" + shopDO.getNumber());
		System.out.println("剩余库存：" + shopDO1.getNumber());
		System.out.println("==================================================");
	}

	public static ShopDO getShopByPort(int port) throws Exception {
		ShopDO shopDO = new ShopDO();
		String sql = "SELECT * FROM shop WHERE port_status =1 and port =" + port;
		dBHelper1 = new DBHelper1(sql);
		ResultSet ret = dBHelper1.pst.executeQuery();
		while (ret.next()) {
			shopDO.setId(Long.parseLong(ret.getString("id")));
			shopDO.setShopName(ret.getString("shop_name"));
			shopDO.setShopPrice(Long.parseLong(ret.getString("sale_price")));
			shopDO.setNumber(Integer.parseInt(ret.getString("number")));
			shopDO.setPort(Integer.parseInt(ret.getString("port")));
			shopDO.setPortStatus(Integer.parseInt(ret.getString("port_status")));
		}
		ret.close();
		dBHelper1.close();// 关闭连接
		return shopDO;
	}

	public static ShopDO getShopByShopId(Long shopId) throws Exception {
		ShopDO shopDO = new ShopDO();
		String sql = "SELECT * FROM shop where id=" + shopId;
		dBHelper1 = new DBHelper1(sql);
		ResultSet ret = dBHelper1.pst.executeQuery();
		while (ret.next()) {
			shopDO.setId(Long.parseLong(ret.getString("id")));
			shopDO.setShopName(ret.getString("shop_name"));
			shopDO.setShopPrice(Long.parseLong(ret.getString("sale_price")));
			shopDO.setNumber(Integer.parseInt(ret.getString("number")));
			shopDO.setPort(Integer.parseInt(ret.getString("port")));
			shopDO.setPortStatus(Integer.parseInt(ret.getString("port_status")));
		}
		ret.close();
		dBHelper1.close();// 关闭连接
		return shopDO;
	}

	public static boolean deductionShopById(Long shopId) throws Exception {
		String sql = "UPDATE shop SET number = number-1 WHERE id =" + shopId;
		dBHelper1 = new DBHelper1(sql);
		int ret = dBHelper1.pst.executeUpdate();
		// 关闭连接
		dBHelper1.close();
		return ret > 0;
	}
}
