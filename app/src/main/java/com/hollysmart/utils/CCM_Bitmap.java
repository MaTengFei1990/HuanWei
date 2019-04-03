package com.hollysmart.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CCM_Bitmap {
	/**
	 * 得到一个压缩后的bitmap来防止内存溢出
	 * 
	 * @param pathName
	 *            文件所在路径，包含文件名
	 * @param 
	 */
	public static Bitmap getSampleBitmap(String pathName, int size) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 不返回实际的bitmap不给其分配内存空间
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = size;
//		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		
		Log.d("com.test", ""+opts.inSampleSize);
		// 重新读入图片，注意这次要把 options.inJustDecodeBounds 设为false 给Bitmap分配内存空间
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}
	/**
	 * 将bitmap保存成文件
	 * 
	 * @param pathName
	 *            文件所要保存的路径，包含文件名
	 */
	public static boolean getBitmapToFile(Bitmap bitmap, String pathName) {
		File file = new File(pathName);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
				return true;
			} else
				return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Get bitmap from specified ImageBean path
	 * 
	 * @param imgPath
	 * @return
	 */
	public Bitmap getBitmap(String imgPath) {
		// Get bitmap through ImageBean path
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = false;
		newOpts.inPurgeable = true;
		newOpts.inInputShareable = true;
		// Do not compress
		newOpts.inSampleSize = 1;
		newOpts.inPreferredConfig = Config.RGB_565;
		return BitmapFactory.decodeFile(imgPath, newOpts);
	}
	
	/**
	 * Store bitmap into specified ImageBean path
	 * 
	 * @param bitmap
	 * @param outPath
	 * @throws FileNotFoundException
	 */
	public void storeImage(Bitmap bitmap, String outPath) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream(outPath);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
	}
	
	/**
	 * Compress ImageBean by pixel, this will modify ImageBean width/height.
	 * Used to get thumbnail
	 * 
	 * @param imgPath ImageBean path
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @return
	 */
	public static Bitmap ratio(String imgPath, float pixelW, float pixelH) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        // Get bitmap info, but notice that bitmap is null now  
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,newOpts);
          
        newOpts.inJustDecodeBounds = false;  
        int w = newOpts.outWidth;  
        int h = newOpts.outHeight;  
        // 想要缩放的目标尺寸
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
	    float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
        int be = 1;//be=1表示不缩放  
        if (w >= h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
            be = (int) (newOpts.outWidth / ww);  
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
            be = (int) (newOpts.outHeight / hh);  
        }  
        if (be <= 0) be = 1;  
        newOpts.inSampleSize = be;//设置缩放比例
        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        // 压缩好比例大小后再进行质量压缩
//        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap;
	}
	
	/**
	 * Compress ImageBean by size, this will modify ImageBean width/height.
	 * Used to get thumbnail
	 * 
	 * @param ImageBean
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @return
	 */
	private int rate;
	public Bitmap ratio(Bitmap image, float pixelW, float pixelH) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		rate= 100;
		image.compress(Bitmap.CompressFormat.JPEG, rate, os);
		
	    while (os.toByteArray().length / 1024 > 600) {  //判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出    
	    	os.reset();//重置baos即清空baos
	    	rate -= 20;
	    	image.compress(Bitmap.CompressFormat.JPEG, rate, os);//这里压缩20%，把压缩后的数据存放到baos中
		}
	    
	    ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
	    BitmapFactory.Options newOpts = new BitmapFactory.Options();
	    //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
	    newOpts.inJustDecodeBounds = true;
	    newOpts.inPreferredConfig = Config.RGB_565;
	    Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);
	    newOpts.inJustDecodeBounds = false;  
	    int w = newOpts.outWidth;  
	    int h = newOpts.outHeight;  
	    float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
	    float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
	    //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
	    int be = 1;//be=1表示不缩放  
	    if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
	        be = (int) (newOpts.outWidth / ww);  
	    } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
	        be = (int) (newOpts.outHeight / hh);  
	    }  
	    if (be <= 0) be = 1;  
	    newOpts.inSampleSize = be;//设置缩放比例  
	    //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
	    is = new ByteArrayInputStream(os.toByteArray());
	    bitmap = BitmapFactory.decodeStream(is, null, newOpts);
	    //压缩好比例大小后再进行质量压缩
//	    return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
	    return bitmap;
	}
	
	/**
	 * Compress by quality,  and generate ImageBean to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param maxSize target will be compressed to be smaller than this size.(kb)
	 * @throws IOException
	 */
	public void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// scale
		int options = 100;
		// Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while ( os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
        	os.reset();
        	// interval 10
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }
        
        // Generate compressed ImageBean file
        FileOutputStream fos = new FileOutputStream(outPath);
        fos.write(os.toByteArray());  
        fos.flush();  
        fos.close();  
	}
	
	/**
	 * Compress by quality,  and generate ImageBean to the path specified
	 * 
	 * @param imgPath
	 * @param outPath
	 * @param maxSize target will be compressed to be smaller than this size.(kb)
	 * @param needsDelete Whether delete original file after compress
	 * @throws IOException
	 */
	public void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean needsDelete) throws IOException {
		compressAndGenImage(getBitmap(imgPath), outPath, maxSize);
		
		// Delete original file
		if (needsDelete) {
			File file = new File(imgPath);
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	/**
	 * Ratio and generate thumb to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @throws FileNotFoundException
	 */
	public void ratioAndGenThumb(Bitmap image, String outPath, float pixelW, float pixelH) throws FileNotFoundException {
		Bitmap bitmap = ratio(image, pixelW, pixelH);
		storeImage( bitmap, outPath);
	}
	
	/**
	 * Ratio and generate thumb to the path specified
	 * 
	 * @param image
	 * @param outPath
	 * @param pixelW target pixel of width
	 * @param pixelH target pixel of height
	 * @param needsDelete Whether delete original file after compress
	 * @throws FileNotFoundException
	 */
	public void ratioAndGenThumb(String imgPath, String outPath, float pixelW, float pixelH, boolean needsDelete) throws FileNotFoundException {
		Bitmap bitmap = ratio(imgPath, pixelW, pixelH);
		storeImage( bitmap, outPath);
		
		// Delete original file
				if (needsDelete) {
					File file = new File(imgPath);
					if (file.exists()) {
						file.delete();
					}
				}
	}
	
	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 *            需要修改的图片
	 * @param pixels
	 *            圆角的弧度
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	
	/**     
     * 缩放图片     
     * @param bmp     
     * @param width     
     * @param height     
     * @return     
     */      
    public static Bitmap PicZoom(Bitmap bmp, int width, int height) {
        int bmpWidth = bmp.getWidth();      
        int bmpHeght = bmp.getHeight();      
        Matrix matrix = new Matrix();
        matrix.postScale((float) width / bmpWidth, (float) height / bmpHeght);      
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeght, matrix, true);
    }


    /**
	 * 网络获取Bitmap
	 * @param UrlPath
	 * @return
     */
	private Bitmap getInternetPicture(String UrlPath) {
		Bitmap bm = null;
		// 1、确定网址
		// http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg
		// 2、获取Uri
		try {
			URL uri = new URL(UrlPath);

			// 3、获取连接对象、此时还没有建立连接
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			// 4、初始化连接对象
			// 设置请求的方法，注意大写
			connection.setRequestMethod("GET");
			// 读取超时
			connection.setReadTimeout(5000);
			// 设置连接超时
			connection.setConnectTimeout(5000);
			// 5、建立连接
			connection.connect();

			// 6、获取成功判断,获取响应码
			if (connection.getResponseCode() == 200) {
				// 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
				InputStream is = connection.getInputStream();
				// 8、从流中读取数据，构造一个图片对象GoogleAPI
				bm = BitmapFactory.decodeStream(is);
				// 9、把图片设置到UI主线程
				// ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；

				Log.i("", "网络请求成功");

			} else {
				Log.v("tag", "网络请求失败");
				bm = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bm;

	}

}
