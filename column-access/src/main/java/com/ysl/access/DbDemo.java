package com.ysl.access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取表结构, 用户提供权限设置接口
 *
 * @author YSL
 * 2018-08-27 11:10
 */
public class DbDemo {

        public static void main(String[] args) {
            List<String> tableNames = getTableNames();
            System.out.println("tableNames:" + tableNames);
            for (String tableName : tableNames) {
                System.out.println("Table：" + tableName);
                System.out.println("ColumnNames:" + getColumnNames(tableName));
                System.out.println("ColumnTypes:" + getColumnTypes(tableName));
                System.out.println("ColumnComments:" + getColumnComments(tableName));
                System.out.println("");
            }
        }


        private static final String DRIVER = "com.mysql.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/access?characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&allowMultiQueries=true";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "123456";

        private static final String SQL = "SELECT * FROM ";// 数据库操作

        static {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取数据库连接
         *
         * @return
         */
        public static Connection getConnection() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }

        /**
         * 关闭数据库连接
         * @param conn
         */
        public static void closeConnection(Connection conn) {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 获取数据库下的所有表名
         */
        public static List<String> getTableNames() {
            List<String> tableNames = new ArrayList<>();
            Connection conn = getConnection();
            ResultSet rs = null;
            try {
                //获取数据库的元数据
                DatabaseMetaData db = conn.getMetaData();
                //从元数据中获取到所有的表名
                rs = db.getTables(null, null, null, new String[] { "TABLE" });
                while(rs.next()) {
                    tableNames.add(rs.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return tableNames;
        }

        /**
         * 获取表中所有字段名称
         * @param tableName 表名
         * @return
         */
        public static List<String> getColumnNames(String tableName) {
            List<String> columnNames = new ArrayList<>();
            //与数据库的连接
            Connection conn = getConnection();
            PreparedStatement pStemt = null;
            String tableSql = SQL + tableName;
            try {
                pStemt = conn.prepareStatement(tableSql);
                //结果集元数据
                ResultSetMetaData rsmd = pStemt.getMetaData();
                //表列数
                int size = rsmd.getColumnCount();
                for (int i = 0; i < size; i++) {
                    columnNames.add(rsmd.getColumnName(i + 1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pStemt != null) {
                    try {
                        pStemt.close();
                        closeConnection(conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return columnNames;
        }

        /**
         * 获取表中所有字段类型
         * @param tableName
         * @return
         */
        public static List<String> getColumnTypes(String tableName) {
            List<String> columnTypes = new ArrayList<>();
            //与数据库的连接
            Connection conn = getConnection();
            PreparedStatement pStemt = null;
            String tableSql = SQL + tableName;
            try {
                pStemt = conn.prepareStatement(tableSql);
                //结果集元数据
                ResultSetMetaData rsmd = pStemt.getMetaData();
                //表列数
                int size = rsmd.getColumnCount();
                for (int i = 0; i < size; i++) {
                    columnTypes.add(rsmd.getColumnTypeName(i + 1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pStemt != null) {
                    try {
                        pStemt.close();
                        closeConnection(conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return columnTypes;
        }

        /**
         * 获取表中字段的所有注释
         * @param tableName
         * @return
         */
        public static List<String> getColumnComments(String tableName) {
            List<String> columnTypes = new ArrayList<>();
            //与数据库的连接
            Connection conn = getConnection();
            PreparedStatement pStemt = null;
            String tableSql = SQL + tableName;
            List<String> columnComments = new ArrayList<>();//列名注释集合
            ResultSet rs = null;
            try {
                pStemt = conn.prepareStatement(tableSql);
                rs = pStemt.executeQuery("show full columns from " + tableName);
                while (rs.next()) {
                    columnComments.add(rs.getString("Comment"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                        closeConnection(conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return columnComments;
        }
}
