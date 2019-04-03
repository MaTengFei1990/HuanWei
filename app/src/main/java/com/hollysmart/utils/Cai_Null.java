package com.hollysmart.utils;

public class Cai_Null {

	/**
	 * 处理 赋值为空问题
	 * @param str
	 * @return
	 */
	public static String setText(String str){
		if (str ==  null || str.trim().equals("") || str.trim().equals("null")|| str.trim().equals("NULL")) {
			return "暂无";
		}else {
			return ToDBC(str);
		}
	}
	
    /**
     * 半角转换为全角 
     *  
     * @param input 
     * @return 
     */  
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();  
        for (int i = 0; i < c.length; i++) {  
            if (c[i] == 12288) {  
                c[i] = (char) 32;  
                continue;  
            }  
            if (c[i] > 65280 && c[i] < 65375)  
                c[i] = (char) (c[i] - 65248);  
        }  
        return new String(c);
    }
    
}
