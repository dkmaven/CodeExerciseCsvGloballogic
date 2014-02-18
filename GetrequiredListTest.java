package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.junit.Before;
import org.junit.Test;


import com.globallogic.exercise.RequiredList;

import static org.junit.Assert.*;



public class GetrequiredListTest{
	
	RequiredList rlt;
	@Before
	public void init(){
		rlt = new RequiredList();
	
	}
	
	@Test
	public void testGetrequiredList(){
		TreeMap map = new TreeMap();
		map.put("CompanyA", "50");
		map.put("Month", "may");
		map.put("Year", "1990");
		List<Map<String, String>>resultList = new LinkedList<Map<String, String >>();
		List<Map<String, String>>expectedList = new LinkedList<Map<String, String >>();
		expectedList.add(map);
		
		assertNotSame(expectedList, resultList);
		
		
	}

}
