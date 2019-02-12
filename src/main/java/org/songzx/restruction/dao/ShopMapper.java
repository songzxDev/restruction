package org.songzx.restruction.dao;

import org.songzx.restruction.model.ShopDO;
import org.songzx.restruction.util.DBHelper2;

public class ShopMapper {
	/**
	 * 根据出货口查询可售商品信息
	 * 
	 * @param port 出货口
	 * @return 可售商品信息
	 * @throws Exception
	 */
	public ShopDO getShopByPort(int port) {
		DBHelper2 dBHelper2 = new DBHelper2();
		String sql = "SELECT * FROM shop WHERE port_status =1 and port =" + port;
		return (ShopDO) dBHelper2.findOne(sql, ShopDO.class);
	}

	/**
	 * 根据商品ID获取商品信息
	 * 
	 * @param shopId 商品ID
	 * @return 商品信息
	 * @throws Exception
	 */
	public ShopDO getShopByShopId(Long shopId) {
		DBHelper2 dBHelper2 = new DBHelper2();
		String sql = "SELECT * FROM shop where id=" + shopId;
		return (ShopDO) dBHelper2.findOne(sql, ShopDO.class);
	}

	public boolean deductionShopById(Long shopId) {
		DBHelper2 dBHelper2 = new DBHelper2();
		String sql = "UPDATE shop SET number = number-1 WHERE id =" + shopId;
		return dBHelper2.executeUpdate(sql);
	}
}
