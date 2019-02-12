package org.songzx.restruction.model;

import java.io.Serializable;

public class ShopDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015050754565443479L;
	/**
	 * 商品ID
	 */
	private Long id;

	/**
	 * 商品名称
	 */
	private String shopName;

	/**
	 * 商品价格
	 */
	private Long shopPrice;

	/**
	 * 商品可售库存
	 */
	private Integer number;

	/**
	 * 出货口
	 */
	private Integer port;

	/**
	 * 出货口状态
	 */
	private Integer portStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Long shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(Integer portStatus) {
		this.portStatus = portStatus;
	}

}
