package com.hollysmart.utils.zip;

import android.os.AsyncTask;

import com.hollysmart.utils.FileTool;

import java.io.File;

public class ZipTool extends AsyncTask<Void, Void, Boolean> {

    private String path;
    private String fileName;
    private ZipTollIF zipTollIF;
    public ZipTool(String path, String fileName, ZipTollIF zipTollIF) {
        this.path = path;
        this.fileName = fileName;
        this.zipTollIF = zipTollIF;
    }

    @Override
    protected Boolean doInBackground(Void... arg0) {
        try {
            XZip.UnZipFolder(path + fileName, path);
        } catch (Exception e) {
            try {
                CAI_UnZip.Unzip(path + fileName, path);
            }catch (Exception ep){
                ep.printStackTrace();
                return false;
            }
        }
        FileTool.deteleFiles(new File(path + fileName));
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        zipTollIF.isOk(result);
    }

    public interface ZipTollIF {
        void isOk(boolean isOk);
    }

}
