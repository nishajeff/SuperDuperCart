package model;

import java.util.HashMap;


public class Shopping {
	
	private HashMap<String,Cart> map;
	public Shopping(){
		map=new HashMap<String,Cart>();
	}
	
	public HashMap<String, Cart> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Cart> map) {
		this.map = map;
	}

	
	public void putToMap(String key,Cart value){
		map.put(key, value);
	}
}
