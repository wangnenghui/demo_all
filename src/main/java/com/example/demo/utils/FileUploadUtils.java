package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

	public static String uploadPictures(MultipartFile[] file, HttpServletRequest request) throws Exception {
		// 获得物理路径webapp所在路径
		String urerId = request.getParameter("userId");
		String path = "";
		StringBuffer listImagePath = new StringBuffer();
		for (MultipartFile mf : file) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				// 获得文件后缀名称
				String imageName = contentType.substring(contentType.indexOf("/") + 1);
				System.out.println("imageName：" + imageName);
				boolean flag = false;
				if (imageName != null) {
					String st[] = { "JPG", "JPEG", "PNG", "GIF" };
					for (int i = 0; i < st.length; i++) {
						if (imageName.equalsIgnoreCase(st[i])) {
							flag = true;
							break;
						}
					}
				}
				if (imageName == null || !flag) {
					return "文件格式不对，请检查文件格式！";
				}

				path = "e:" + File.separator + "static" + File.separator + "images" + File.separator + urerId;
				String realfile = path + File.separator + uuid + "." + imageName;
				File fileP = new File(path);
				if (fileP.isDirectory()) {
					System.out.println("目录存在，直接上传");
					mf.transferTo(new File(realfile));
				} else {
					System.out.println("目录不存在，创建目录");
					fileP.mkdirs();
					mf.transferTo(new File(realfile));
				}
				listImagePath.append(realfile + "|");
			}
		}
		listImagePath.deleteCharAt(listImagePath.length() - 1);
		System.out.println(listImagePath);
		return listImagePath.toString();
	}

	public static void getAppFile(HttpServletResponse response, String fileName) {
		FileInputStream fis = null;
		File file = new File(fileName);
		 String filename = file.getName();
		response.setHeader("Access-Control-Allow-Origin", "*");// 设置该图片允许跨域访问
		 response.reset();
         // 设置response的Header
         response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
         response.addHeader("Content-Length", "" + file.length());
         response.setContentType("application/octet-stream");
		try {
			fis = new FileInputStream(file);
			IOUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.fillInStackTrace();
		}

	}
	
}