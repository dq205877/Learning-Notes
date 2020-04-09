package com.example.springboot.first_springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping("/hi")
	@ResponseBody
	public Map<String,Object> show(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dq", "hi,springboot!");
		return map;
	}
	
	@RequestMapping("/st")
	@ResponseBody
	public List<Map<String, Object>>  st(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dq", "hi,springboot!");
		list.add(map);
		return list;
	}
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    @RequestMapping("/getUsers")
	    public List<Map<String, Object>> getDbType(){
	        String sql = "select * from student";
	        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
	        for (Map<String, Object> map : list) {
	            Set<Entry<String, Object>> entries = map.entrySet( );
	                if(entries != null) {
	                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
	                    while(iterator.hasNext( )) {
	                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
	                    Object key = entry.getKey( );
	                    Object value = entry.getValue();
	                    System.out.println(key+":"+value);
	                }
	            }
	        }
	        return list;
	    }
	    
	    @RequestMapping("/user/{id}")
	    public Map<String,Object> getUser(@PathVariable String id){
	        Map<String,Object> map = null;
	        
	        List<Map<String, Object>> list = getDbType();
	        
	        for (Map<String, Object> dbmap : list) {
	            
	            Set<String> set = dbmap.keySet();
	            
	            for (String key : set) {
	                if(key.equals("id")){    
	                    if(dbmap.get(key).equals(id)){
	                        map = dbmap;
	                    }
	                }
	            }
	        }
	        
	        if(map==null)
	            map = list.get(0);
	        return map;
	    }

}
