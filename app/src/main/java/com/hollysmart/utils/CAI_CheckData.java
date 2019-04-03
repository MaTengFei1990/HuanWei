package com.hollysmart.utils;

import java.io.File;
import java.util.List;

public class CAI_CheckData {
	public boolean checkData(List<String> DirectoryList, String dataSQL){
		for (int i = 0; i < DirectoryList.size(); i++) {
			File file = new File(DirectoryList.get(i));
			if (!file.exists())
				return false;
			if (!file.isDirectory())
				return false;

			if (file.list() == null)
				return false;

			if (file.list().length ==0)
				return false;
		}

		File file = new File(dataSQL);
		if (!file.exists())
			return false;
		
		return true;
	}
	
}
