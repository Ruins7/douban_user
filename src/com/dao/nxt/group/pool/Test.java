package com.dao.nxt.group.pool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.iterators.UniqueFilterIterator;




public class Test {

	 
	public static void main(String[] args) throws Exception{
		/* Things t = new Things() ;
		t.setType(100);
		 
		new Test().sss(t); */
		
		/*List<Integer> footstep_id = new ArrayList<Integer>();
		footstep_id.add(4);
		footstep_id.add(2);
		footstep_id.add(3);
		footstep_id.add(1);
		footstep_id.add(2);
		footstep_id.add(2);
		footstep_id.add(3);
		 new Test().footStepquchong(footstep_id);
		*/
		
		
		//map排序测试
		/*Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(3, 4);
        map.put(2, 5);
        map.put(1, 3);
        map.put(4, 7);

        //这里将map.entrySet()转换成list
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //升序排序
			@Override
			public int compare(java.util.Map.Entry<Integer, Integer> o1,
					java.util.Map.Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				   return o2.getValue().compareTo(o1.getValue());
			}

        });
        Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
        for(Map.Entry<Integer,Integer> mapping:list){
              // System.out.println(mapping.getKey()+":"+mapping.getValue());
               
               map1.put(mapping.getKey(), mapping.getValue());
          }
        for(int m: map1.keySet()){    
			   // System.out.println("key:" + m+ "   value:" + map1.get(m));  			  
			   }  */
   
	//连接池测试
		/*Connection conn =  ConnectionPool.printDriverStats();
		
		Statement stmt = conn.createStatement();
		String sql = "insert into user_root(root_name) values(1111)";
		int affectRows = stmt.executeUpdate(sql);
		
		System.out.println(affectRows + " ....  ");
        stmt.close();*/
		
		PoolService pool = PoolService.getInstance();
		for (int i = 0; i < 100000; i++) {
	
		
		Connection conn = pool.getConnection();
     
		Statement stmt = conn.createStatement();
		String sql = "select sysdate()";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1) + " ....  ");
		}
		pool.releaseConnection(conn);
		}
      //用户浏览记录测试：东西
/*	  UserTools ut = new UserTools();
	  Map<String, LinkedList<Things>> map_things = new HashMap<String, LinkedList<Things>>();
	  LinkedList<Things> list = new LinkedList<Things>();
	  Things t1 = new Things();
	  Things t2 = new Things();
	  Things t3 = new Things();
	  Things t4 = new Things();
	  Things t5 = new Things();
	  Things t6 = new Things();
	  Things t7 = new Things();
	  Things t8 = new Things();
	  Things t9 = new Things();
	  Things t10 = new Things();
	  t1.setType(1);
	  t2.setType(1);
	  t3.setType(1);
	  t4.setType(1);
	  t5.setType(2);
	  t6.setType(2);
	  t7.setType(3);
	  t8.setType(3);
	  t9.setType(3);
	  t10.setType(4);
	  list.add(t1);
	  list.add(t2);
	  list.add(t3);
	  list.add(t4);
	  list.add(t5);
	  list.add(t6);
	  list.add(t7);
	  list.add(t8);
	  list.add(t9);
	  list.add(t10);
	  map_things.put("123456", list);
	 List<Things> list1 = ut.getLinkedListBaseOnFootStep(map_things, new Things());
        
     System.out.println("系统推荐的个数："+list1.size());
     for (Things things : list1) {
		System.out.println(things.getThing_id());
	}*/
}     
	 
	
	public <R> void sss(R r) throws Exception, Exception{
		Field[] field = r.getClass().getDeclaredFields();
		for (Field d : field) {
			d.setAccessible(true);
			 if(d.getName().equalsIgnoreCase("type")){
				System.out.println(d.get(r));
			 }
		}
		
	}
	
	public void footStepquchong(List<Integer> footstep_id){
		List<Map<Integer, Integer>> list = new ArrayList<Map<Integer, Integer>>();
		//去重
				Iterator<Integer> uniqueIterator = new UniqueFilterIterator(footstep_id.iterator());	
				while(uniqueIterator.hasNext()) {
					int i = uniqueIterator.next();
					int num = 0;
					for (Integer integer : footstep_id) {
						if(i == integer){
		                   num += 1;
						}
					}
					Map<Integer, Integer> map = new HashMap<Integer, Integer>();
					map.put(i, num);
					list.add(map);
				}
		
		/////////////////////////////
		
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Map<Integer, Integer> map = (Map<Integer, Integer>) iterator.next();
					for(int m: map.keySet()){    
					   System.out.println("key:" + m+ "   value:" + map.get(m));  			  
					   }  
				}
				
	}	

}
