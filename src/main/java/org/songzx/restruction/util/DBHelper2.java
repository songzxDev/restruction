package org.songzx.restruction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DBHelper2 {
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rst = null;

	/**
	 * 建立数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection() {
		try {
			// 加载数据库驱动程序
			try {
				Class.forName(SQLCommon.driver);
			} catch (ClassNotFoundException e) {
				System.out.println("加载驱动错误");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			// 获取连接
			conn = DriverManager.getConnection(SQLCommon.url, SQLCommon.user, SQLCommon.password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * insert update delete SQL语句的执行的统一方法
	 * 
	 * @param sql SQL语句
	 * @return 执行是否成功
	 */
	public boolean executeUpdate(String sql) {
		try {
			// 获得连接
			conn = this.getConnection();
			// 调用SQL
			pst = conn.prepareStatement(sql);
			// 执行
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// 释放资源
			close();
		}
		return false;
	}

	/**
	 * SQL 查询将查询结果：一行一列
	 * 
	 * @param sql SQL语句
	 * @return 结果集
	 */
	public Object findOne(String sql, Class cz) {
		try {
			// 获得连接
			conn = this.getConnection();
			// 调用SQL
			pst = conn.prepareStatement(sql);
			// 执行
			rst = pst.executeQuery();
			ResultSetMetaData data = rst.getMetaData();
			JSONObject jsonObject = new JSONObject();
			while (rst.next()) {
				for (int i = 1; i <= data.getColumnCount(); i++) {
					// 获取列字段
					String fieldName = data.getColumnName(i);
					jsonObject.put(fieldName, rst.getString(fieldName));
				}
			}
			// JSON转对象
			return JSON.parseObject(jsonObject.toJSONString(), cz);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return null;
	}

	public void close() {
		// 关闭结果集对象
		if (rst != null) {
			try {
				rst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		// 关闭PreparedStatement对象
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		// 关闭Connection 对象
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
