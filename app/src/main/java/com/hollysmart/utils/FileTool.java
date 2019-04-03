package com.hollysmart.utils;

import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by cai on 16/5/30.
 */
public class FileTool {

    /**
     * 创建空白文件
     *
     * @param fileName 需要包含完整目录与文件名
     * @return
     * @throws IOException
     */
    public static File CreateFile(String fileName) throws IOException {
        System.out.println(fileName);
        File file = new File(fileName);
        file.createNewFile();
        return file;
    }

    public static byte[] fileToBetyArray(String filePath) {
        FileInputStream fileInputStream = null;
        File file = new File(filePath);
        byte[] bFile = null;
        try {
            bFile = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char) bFile[i]);
            }
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bFile.clone();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bFile;
    }

    //根据byte数组，生成文件
    public static File getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return file;
        }
    }


    public static String readStringFormFile(File file) {
        String str;
        try {
            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    /**
     * 递归删除文件
     *
     * @param file
     */
    public static void deteleFiles(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] tempList = file.listFiles();
                for (File f : tempList) {
                    deteleFiles(f);
                }
            }
            file.delete();
        }
    }

    /**
     * 复制文件夹下所有文件到指定的文件夹
     *
     * @param path     要复制的文件
     * @param copyPath 复制到的目标文件
     * @throws IOException
     */
    public static void copy(String path, String copyPath) throws IOException {
        File filePath = new File(path);
        DataInputStream read;
        DataOutputStream write;
        if (filePath.isDirectory()) {
            CreateDir(copyPath);
            File[] list = filePath.listFiles();
            for (int i = 0; i < list.length; i++) {
                String newPath = path + File.separator + list[i].getName();
                String newCopyPath = copyPath + File.separator + list[i].getName();
                copy(newPath, newCopyPath);
            }
        } else if (filePath.isFile()) {
            read = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(path)));
            write = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(copyPath)));
            byte[] buf = new byte[1024 * 512];
            while (read.read(buf) != -1) {
                write.write(buf);
            }
            read.close();
            write.close();
        } else {
            System.out.println("请输入正确的文件名或路径名");
        }
    }


    public static File CreateDir(String folder) {
        File dir = new File(folder);
        dir.mkdirs();
        return dir;
    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filepath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


}
