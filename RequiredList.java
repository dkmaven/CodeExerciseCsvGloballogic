	package com.globallogic.exercise;
	
	import java.sql.SQLException;
	import java.util.Collections;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;
	
	public class RequiredList {
	
		public RequiredList() {
			super();
			// TODO Auto-generated constructor stub
		}



		private static LinkedList<Map<String, String>> reqiredList;
	
		public static void main(String[] args) throws SQLException {
			String path ="C:\\Users\\Deepak\\Desktop\\Exercise";
			String fileName="data.csv";
			CSVRead csvread = new CSVRead(path, fileName);
			try {
				List<Map<String, String>> lst =csvread.getData();
				getrequiredList(lst);
	
			}  catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
	
	
		private static List<Map<String, String>> getrequiredList(List<Map<String, String>> lst) {
			reqiredList= new LinkedList<Map<String, String >>();
			Map<String,String> header =lst.get(0);
			Set<String> set=header.keySet();
			set.remove("Month");
			set.remove("Year");
			for (String string : set) {
				List<String> list = new LinkedList<String>();
				for (Map<String, String> map : lst) {
					list.add(map.get(string));
				}
				reqiredList.add(lst.get(maxIndex(list)));
			}
			System.out.println(reqiredList);
			return reqiredList;
	
		}
	
	
	
		private static int maxIndex(List<String> list) {
			String max=Collections.max(list);
			int maxIndex = list.indexOf(max);
			return maxIndex;
		}	
	
	
	
	}
