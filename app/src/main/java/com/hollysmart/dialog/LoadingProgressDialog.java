package com.hollysmart.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingProgressDialog {
	private ProgressDialog m_pDialog;
	private String title = "提示",message = "数据读取中，请稍候。";
	private int num;
	public final int STYLE_SPINNER = ProgressDialog.STYLE_SPINNER;
	public final int STYLE_HORIZONTAL = ProgressDialog.STYLE_HORIZONTAL;
	public ProgressDialog create(Context cont, int STYLE){
		m_pDialog = new ProgressDialog(cont);
		num = 0;
		m_pDialog.setProgressStyle(STYLE);
		
		m_pDialog.setTitle(title);
		
		m_pDialog.setMessage(message);
		
		m_pDialog.setIndeterminate(false);
		
		m_pDialog.setCancelable(true);
		
		return m_pDialog;
	}
	public void cancel(){
		if(m_pDialog!=null){
			m_pDialog.cancel();
			num = 0;
		}
	}
	
	public void setCancelable(boolean b){
		m_pDialog.setCancelable(b);
	}
	public void show(){
		if(m_pDialog!=null)
			m_pDialog.show();
	}
	public void ProgresMAX(int MAX){
		if(m_pDialog!=null)
			m_pDialog.setMax(MAX);
	}
	public void ProgresValues(int value){
		if(m_pDialog!=null)
			m_pDialog.setProgress(value);
	}
	public void ProgresValues(){
		if(m_pDialog!=null)
			m_pDialog.setProgress(++num);
	}
	public void setMessage(String message){
		if(message!=null&&!message.equals(""))
			this.message = message;
		if(m_pDialog!=null)
			m_pDialog.setMessage(this.message);
	}
	public void setTitle(String title){
		if(title!=null&&!title.equals(""))
			this.title = title;
		if(m_pDialog!=null)
			m_pDialog.setTitle(this.title);
	}
	public boolean isActive(){
		return m_pDialog == null? false:true;
	}
}
