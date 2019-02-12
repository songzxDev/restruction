package org.songzx.restruction.domain;

import java.util.List;

import org.songzx.restruction.model.Strategy;

public class VendingMachine4 {
	// 持有一个具体策略的对象
	private Strategy strategy;

	/**
	 * 构造函数，传入一个具体策略对象
	 * 
	 * @param strategy 具体策略对象
	 */
	public VendingMachine4(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 策略方法
	 */
	public void vendingMachine(String userName, long price, List<Integer> portList) {
		strategy.vendingMachine(userName, price, portList);
	}
}
