package com.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Session {

	public Session() {

	}

	public void save(Object o) {
		StringBuffer sql = new StringBuffer("insert into ");
		String tablename = (o.getClass().getSimpleName()).toLowerCase();
		sql.append(tablename + " values(default,");
		Field[] field = o.getClass().getDeclaredFields();
		for (int i = 0; i < field.length - 1; i++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		System.out.println(sql.toString());
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			Method[] method = o.getClass().getMethods();
			int i = 1;
			for (Field f : field) {
				if (i != 1) {
					String fieldname = f.getName();
					String methods = "get"
							+ (fieldname.substring(0, 1)).toUpperCase()
							+ fieldname.substring(1);
					System.out.println(methods);
					Method setMethod = o.getClass().getDeclaredMethod(methods);
					ps.setObject(i - 1, setMethod.invoke(o, null));
					System.out.println(setMethod.invoke(o, null));
				}
				i++;
			}
			ps.execute();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
