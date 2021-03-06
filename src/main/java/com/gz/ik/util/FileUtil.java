package com.gz.ik.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {
	private static String seperator = System.getProperty("file.separator");
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss"); // 时间格式化的格式
	private static final Random r = new Random();

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "F:/ik/image/";
		} else {
			basePath = "/home/ik_img/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getUserImgPath() {
		String imgPath = "/upload/images/item/user/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}
	
	public static String getCourseImgPath() {
		String imgPath = "/upload/images/item/course/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}
	
	public static String getNewsImgPath() {
		String imgPath = "/upload/images/item/news/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}
	
	public static String getCompanyImgPath() {
		String imgPath = "/upload/images/item/companyimg/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}
	
	public static String getTeacherImgPath() {
		String imgPath = "/upload/images/item/teacher/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}

	public static String getShopImagePath(long shopId) {
		StringBuilder shopImagePathBuilder = new StringBuilder();
		shopImagePathBuilder.append("/upload/images/item/shop/");
		shopImagePathBuilder.append(shopId);
		shopImagePathBuilder.append("/");
		String shopImagePath = shopImagePathBuilder.toString().replace("/",
				seperator);
		return shopImagePath;
	}

	public static String getRandomFileName() {
		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		return nowTimeStr + rannum;
	}

	public static void deleteFile(String storePath) {
		File file = new File(getImgBasePath() + storePath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			file.delete();
		}
	}
}
