package com.hollysmart.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CCM_SharePreference {
	private SharedPreferences sp;
    private Editor editor;
    private final static int MODE = Context.MODE_APPEND;
    public CCM_SharePreference(Context context, String SP_NAME) {
        try {
            sp = context.getSharedPreferences(SP_NAME, MODE);
            editor = sp.edit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private final static String SP_NAME = "preferences";

  public CCM_SharePreference(Context context) {

      try {
          sp = context.getSharedPreferences(SP_NAME, MODE);
          editor = sp.edit();
      }catch (Exception e){
          e.printStackTrace();
      }

  }
    public boolean saveString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();   
    }
    public boolean saveInt(String key, int value) {
    	editor.putInt(key, value);
    	return editor.commit();   
    }
    public boolean saveFloat(String key, float value) {
    	editor.putFloat(key, value);
    	return editor.commit();
    }
    public boolean saveBoolean(String key, boolean value){
    	 editor.putBoolean(key, value);
         return editor.commit();   
    }
  
    public String readString(String key, String defValue) {
        return sp.getString(key, defValue);   
    }
    
    public boolean readBoolean(String key, boolean set){
        return sp.getBoolean(key, set);   
   }
    public int readInt(String key, int defValue) {
    	return sp.getInt(key, defValue);
    }

    public float readFloat(String key, float defValue) {
    	return sp.getFloat(key, defValue);
    }


}
