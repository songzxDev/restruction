package org.songzx.restruction.domain;

import java.util.ArrayList;
import java.util.List;

import org.songzx.restruction.model.Strategy;
import org.songzx.restruction.model.StrategyListImpl;
import org.songzx.restruction.model.StrategyOneImpl;

public class Test {
	public static void main(String[] args) {
		String userName = "哈比";
		long price = 10L;
		List<Integer> portList = new ArrayList<Integer>();
		portList.add(1);
		portList.add(2);

		// 这里可以采用抽象工厂模式哦！
		Strategy strategy = null;
		// 选择并创建需要使用的策略对象
		if (portList != null && portList.size() == 1) {
			strategy = new StrategyOneImpl();
		} else {
			strategy = new StrategyListImpl();
		}
		VendingMachine4 vendingMachine4 = new VendingMachine4(strategy);
		vendingMachine4.vendingMachine(userName, price, portList);
	}
}
