	package com.globallogic.exercise;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
	import java.util.TreeMap;
	
	
	/**
	 * Class to read the csv file
	 * 
	 * @author Deepak Kr Singh
	 * 
	 */
	public class CSVRead {
	
	
		/**
		 * variable declaration with default initialization
		 */
		private Connection con = null;
		private ResultSet rs = null;
		private Statement stmt = null;
		private ResultSetMetaData metadata = null;
		private int noofColumn = 0;
		private String filePath = null;
		private String tableName = null;
	
	
		public CSVRead(String path, String fileName) {
			this.filePath = path;
			this.tableName = fileName;
		
		}
	
		/**
		 * Method to initialize the connection from csv file for read file
		 * 
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		private void getConnection() throws SQLException, ClassNotFoundException {
			String driver = "jdbc:odbc:Driver="
				+ "{Microsoft Text Driver (*.txt; *.csv)};" + "DBQ=" + filePath
				+ ";Extensions=asc,csv,tab,txt";
			try {
				if (con != null) {
					System.out.println("Already have connection.....");
				} else {
					System.out.println("creating connection.....");
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection(driver, "", "");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw e;
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
	
	
		/**
		 * Method to read the data from csv file
		 * 
		 * @param attrName
		 * @return
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		public List<Map<String, String>> getData() throws SQLException, ClassNotFoundException {
			try {
				getConnection();
				String sql = prepareSelectSQl();
				List metadataRows = new ArrayList();
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				metadata = rs.getMetaData();
				noofColumn = metadata.getColumnCount();
				while (rs.next()) {
					TreeMap map = new TreeMap();
					for (int i = 1; i <= noofColumn; i++) {
						String field = metadata.getColumnName(i);
						map.put(field, rs.getString(i));
					}
					metadataRows.add(map);
				}
				return metadataRows;
			}  finally {
				closeConnection();
			}
		}
	
	
		/**
		 * Method to prepare sql to read the record from csv file
		 * 
		 * @return
		 */
		private String prepareSelectSQl() {
			StringBuffer sql = null;
			sql = new StringBuffer();
			sql.append("select *");
			sql.append(" from ");
			sql.append(tableName);
			String sql1 = sql.toString();
			return sql1;
	
		}
		/**
		 * Method to close the connection from CSV file
		 * 
		 * @throws SQLException
		 */
		public void closeConnection() throws SQLException {
			cleanResult();
			if (con != null) {
				try {
					con.close();
					con = null;
				} catch (SQLException e) {
					throw e;
				}
			}
		}
	
		private void cleanResult() throws SQLException {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					throw e;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					throw e;
				}
			}
		}
	
	
	}
