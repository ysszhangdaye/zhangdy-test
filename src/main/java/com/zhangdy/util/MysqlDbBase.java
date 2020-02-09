package com.zhangdy.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDbBase {

    protected Connection conn;

    public MysqlDbBase(String url, String userName, String pwd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAutoCommit(Boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void closeConn() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResult(ResultSet result) {
        if (result != null) {
            try {
                result.getStatement().close();
            } catch (Exception e) {
            }
        }
    }

    public void closeStatement(PreparedStatement pst) {
        try {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
        } catch (Exception e) {
        }
    }

    public Integer getCountBySql(String sql, List<Object> params) {
        Integer result = 0;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int index = 0;
                for (Object obj : params) {
                    pst.setObject(++index, obj);
                }
            }
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResult(resultSet);
        }
        return result;
    }

    public <T> List<T> findList(String sql, List<Object> params, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            setParams(pst, params);
            resultSet = pst.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                list.add(getResultSetData(resultSet, clazz, metaData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResult(resultSet);
        }
        return list;
    }

    public <T> T findOne(String sql, List<Object> params, Class<T> clazz) {
        T result = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            setParams(pst, params);
            resultSet = pst.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            if (resultSet.next()) {
                result = getResultSetData(resultSet, clazz, metaData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResult(resultSet);
        }
        return result;
    }


    public <T> T getResultSetData(ResultSet resultSet, Class<T> clazz, ResultSetMetaData metaData) {
        T t = null;
        try {
            t = clazz.getConstructor().newInstance();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String fName = metaData.getColumnLabel(i + 1).toLowerCase();
                Object value = resultSet.getObject(fName);
                fName = CustomStringUtils.replaceUnderlineAndfirstToUpper(fName, "_", "");
                Field field = clazz.getDeclaredField(fName);
                String setName = "set" + fName.toUpperCase().substring(0, 1) + fName.substring(1);
                Method setMethod = clazz.getMethod(setName, field.getType());
                setMethod.invoke(t, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    void setParams(PreparedStatement pst, List<Object> params) {
        try {
            if (params != null && params.size() > 0) {
                int index = 0;
                for (Object obj : params) {
                    pst.setObject(++index, obj);
                }
            }
        } catch (Exception e) {

        }

    }


}
