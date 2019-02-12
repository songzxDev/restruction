package org.songzx.restruction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper1 {
	public static final String url = "jdbc:mysql://localhost:3306/restructure?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
	public static final String name = "com.mysql.cj.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "root";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBHelper1(String sql) {
		try {
			// 指定连接类型
			Class.forName(name);
			// 获取连接
			conn = DriverManager.getConnection(url, user, password);
			// 准备执行语句
			pst = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
