package org.songzx.restruction.model;

import java.util.List;

public interface Strategy {
	/**
	 * 策略方法--自动售卖机
	 * 
	 * @param userName 用户
	 * @param price    价格
	 * @param portList 出货口
	 */
	public void vendingMachine(String userName, long price, List<Integer> portList);
}
