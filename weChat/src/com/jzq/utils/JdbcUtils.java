package com.jzq.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	// 1、Statement 处理 插入 删除 更新
	public void excute_stmt(String sql) {
		try {
			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(st, conn);
		}
	}

	// 2、PreparedStatement 处理 插入 删除 更新
	public void excute_pst(String sql, PsSetValues ips) {
		conn = ConnectionFactory.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			// set
			ips.setValue(pst);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pst, conn);
		}
	}

	// 3、Statement 处理查询语句
	public void excute_STquery(String sql, IProcessRS IPS) {
		try {
			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			IPS.ProcessRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, st, conn);
		}
	}

	// 4、PreparedStatement 处理查询语句
	public void excute_PSTquery(String sql, PsSetValues ips, IProcessRS IPS) {
		conn = ConnectionFactory.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			// set
			ips.setValue(pst);
			rs = pst.executeQuery();
			IPS.ProcessRS(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pst, conn);
		}
	}
}
