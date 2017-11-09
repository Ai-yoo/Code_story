package com_daoImp;

import com_basedao.BaseDao;
import com_util.JdbcUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/8
 * Time:19:46
 */

public class BaseDaoImp<T> implements BaseDao<T>{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;
    private List<Object> list = null;
    //LinkedHashMap是一个有顺序的map集合
    private static Map<String, String> map = new LinkedHashMap<String, String>();

    /**
     * 根据id删除记录
     * @param t
     * @param id
     */
    @Override
    public void delete(T t, int id){
        StringBuffer stringBuffer = new StringBuffer("delete from ");
        Class clazz = t.getClass();
        String tableName = clazz.getSimpleName().toLowerCase();
        sql = stringBuffer.append(tableName + " where id=?").toString();
        try {
            connection = JdbcUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseResources(connection, preparedStatement, null);
        }
    }

    /**
     * 修改密码
     * @param t
     * @param password
     * @param id
     */
    @Override
    public void update(T t, String password, int id) {
        Class clazz = t.getClass();
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuffer stringBuffer = new StringBuffer("update " + tableName + " set password=? where id=?");
        sql = stringBuffer.toString();
        try {
            connection = JdbcUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseResources(connection, preparedStatement, null);
        }
    }

    /**
     * 增加
     * @param t
     */
    @Override
    public void insert(T t) {
        StringBuffer stringBuffer = new StringBuffer("insert into ");
        Class clazz = t.getClass();
        String table = clazz.getSimpleName().toLowerCase();
        try {
            Object object = t;
            getField(object);
            stringBuffer.append(table + " values(");
            for (int i = 0; i < map.size(); i++) {
                if (i == map.size() - 1) {
                    stringBuffer.append("?)");
                }else {
                    stringBuffer.append("?,");
                }
            }
            sql = stringBuffer.toString();
            connection = JdbcUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(sql);
            int i=1;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("int".equals(entry.getValue())) {
                    Method method = clazz.getMethod(get(entry.getKey()));
                    System.out.println(method.invoke(object,null));
                    preparedStatement.setInt(i,(int)method.invoke(object,null));
                    i++;
                }
                if ("String".equals(entry.getValue())) {
                    Method method = clazz.getMethod(get(entry.getKey()));
                    preparedStatement.setString(i,(String)method.invoke(object,null));
                    i++;
                }
            }
            preparedStatement.execute();

        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseResources(connection,preparedStatement,null);
        }
    }
//    public List<Object> selectMap(T t, T t1,String s1,String s2) {
//        StringBuffer stringBuffer = new StringBuffer("select * from ");
//        list = new ArrayList<Object>();
//        Class clazz =t.getClass();
//        Class clazz1 = t1.getClass();
//        String table1 = clazz.getSimpleName().toLowerCase();
//        String table2 = clazz1.getSimpleName().toLowerCase();
//        sql = stringBuffer.append(table1 + "," + table2 + " where "+table1+"."+s1+"="+table2+"."+s2).toString().toLowerCase();
//        try {
//            connection = JdbcUtil.getInstance().getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Object object1 = clazz.newInstance();
//                Object object2 = clazz1.newInstance();
//                getField(object1);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        System.out.println(sql);
//        return null;
//    }
    @Override
    public List<Object> selectAll(T t) {
        //初始化字符串
        StringBuffer stringBuffer = new StringBuffer("select * from ");
        //创建对象的容器list集合
        list = new ArrayList<Object>();
        //获取类的类
        Class clazz =t.getClass();
        //获取到类的名字处理拼接sql语句
        sql = stringBuffer.append(clazz.getSimpleName()).toString().toLowerCase();
        try {
            connection = JdbcUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //利用反射获得对象
                Object object = clazz.newInstance();
                getField(object);
                int i = 1;
                //遍历map集合
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if ("int".equals(entry.getValue())) {
                        //利用发射获取实体类中的set方法
                        Method method = clazz.getMethod(getSet(entry.getKey()), int.class);
                        //执行方法
                        method.invoke(object, resultSet.getInt(i));
                        //从结果集中想要获取列值需要一个计数器如果查到的是本列加一
                        i++;
                    }
                    if ("String".equals(entry.getValue())) {
                        Method method = clazz.getMethod(getSet(entry.getKey()), String.class);
                        method.invoke(object, resultSet.getString(i));
                        i++;
                    }
                }
                list.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseResources(connection,preparedStatement,resultSet);
        }
        return list;
    }

    /**
     * 根据id查询
     * @param t
     * @param id
     * @return
     */
    @Override
    public List<Object> selectOne(T t,int id) {
        StringBuffer stringBuffer = new StringBuffer("select * from ");
        list = new ArrayList<Object>();
        Class clazz = t.getClass();
        sql = stringBuffer.append(clazz.getSimpleName()+" where id=?").toString().toLowerCase();
        try {
            connection = JdbcUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Object object = clazz.newInstance();
                getField(t);
                int i = 1;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if ("int".equals(entry.getValue())) {
                        Method method = clazz.getMethod(getSet(entry.getKey()), int.class);
                        method.invoke(object, resultSet.getInt(i));
                        i++;
                    }
                    if ("String".equals(entry.getValue())) {
                        Method method = clazz.getMethod(getSet(entry.getKey()), String.class);
                        method.invoke(object, resultSet.getString(i));
                        i++;
                    }
                }
                list.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseResources(connection,preparedStatement,resultSet);
        }
        return list;
    }

    /**
     * 根据属性名拼接set方法字符串
     * @param str
     * @return
     */
    public static String getSet(String str) {

        return "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 拼接get方法
     * @param str
     * @return
     */
    public static String get(String str) {
        return "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void getField(Object object) {
        Class clazz = object.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String type = field.getGenericType().toString();
            /*如果是String听会带有class java.lang.String截取再放入map中，其他直接放入
            * 把属性名作为键，属性类型作为值*/
            if ("class java.lang.String".equals(type)) {
                int index = type.lastIndexOf(".");
                map.put(field.getName(), type.substring(index + 1));
            } else {
                map.put(field.getName(), field.getGenericType().toString());
            }
        }
    }

}
