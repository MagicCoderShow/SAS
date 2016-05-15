package com.xuping.sas.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@SuppressWarnings({ "unused", "unchecked" })
public class FileUtil {
	public static CommonJson uploadfile(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception{
		CommonJson json=new CommonJson();
		
		String savePath = ConfigReader.getValue("uploadDir")+"/upload/";
		String saveUrl = ConfigReader.getValue("weblink")+"/upload/";
		//生成上传目录
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		StringBuffer tempPath =new StringBuffer();
		//路径--年
		tempPath.append(calendar.get(Calendar.YEAR)).append("/");
		//路径--月
		tempPath.append(calendar.get(Calendar.MONTH)+1).append("/");
		//路径--日
		tempPath.append(calendar.get(Calendar.DATE)).append("/");
		//路径--时
		tempPath.append(calendar.get(Calendar.HOUR_OF_DAY)).append("/");
		
		
		ServletContext application = request.getSession().getServletContext();
//		System.out.println(saveUrl8);
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		// 最大文件大小
		long maxSize = 10000000;
		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			json.setError_msg("请选择文件");
			return json;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			json.setError_msg("上传目录不存在");
			return json;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			json.setError_msg("上传目录没有写权限");
			return json;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> fileItems = upload.parseRequest(request);
		String fileurl="";
		for (FileItem item:fileItems) {
			String path=savePath;
			String url=saveUrl;
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					json.setError_msg("上传文件大小超过限制。");
					return json;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					json.setError_msg("目录名不正确。");
					return json;
				}
				// 创建文件夹
				String uid = UUID.randomUUID().toString().replaceAll("-", "");
				path = path+dirName + "/"+tempPath.toString()+uid+"/";
				url = url+dirName + "/"+tempPath.toString()+uid+"/";
				File saveDirFile = new File(path);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					json.setError_msg("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
					return json;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(path, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					json.setError_msg("上传文件失败。");
					return json;
				}
				
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", url + newFileName);
				//WebUtil.writerJson(response, msg);
				fileurl+=","+url + newFileName;
			}else{
				String name=item.getFieldName();
				String value=item.getString("UTF-8");
				//System.out.println(name);
				//System.out.println(value);
				if(!name.equals("handleUser")){
//					ReflectHelper.setValueByFieldName(object, name, value);
				}
				//request.setAttribute(name, value);
			}
		}
		if(!fileurl.equals("")){
			fileurl=fileurl.substring(1, fileurl.length());
		}
		json.setError_msg(fileurl);
		json.set_success(true);
		
		return json;
	}
	
	public static CommonJson uploadfileMap(HttpServletRequest request, HttpServletResponse response,Map<String,String> params) throws Exception{
		CommonJson json=new CommonJson();
		
		String savePath = ConfigReader.getValue("uploadDir")+"/upload/";
		String saveUrl = ConfigReader.getValue("weblink")+"/upload/";
		//生成上传目录
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		StringBuffer tempPath =new StringBuffer();
		//路径--年
		tempPath.append(calendar.get(Calendar.YEAR)).append("/");
		//路径--月
		tempPath.append(calendar.get(Calendar.MONTH)+1).append("/");
		//路径--日
		tempPath.append(calendar.get(Calendar.DATE)).append("/");
		//路径--时
		tempPath.append(calendar.get(Calendar.HOUR_OF_DAY)).append("/");
		
		
		ServletContext application = request.getSession().getServletContext();
//		System.out.println(saveUrl8);
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		// 最大文件大小
		long maxSize = 10000000;
		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			json.setError_msg("请选择文件");
			return json;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			json.setError_msg("上传目录不存在");
			return json;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			json.setError_msg("上传目录没有写权限");
			return json;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> fileItems = upload.parseRequest(request);
		String fileurl="";
		for (FileItem item:fileItems) {
			String path=savePath;
			String url=saveUrl;
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					json.setError_msg("上传文件大小超过限制。");
					return json;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String dirName = request.getParameter("dir");
				if (dirName == null) {
					dirName = MapUtil.getKey(extMap, fileExt, true);
				}
				if (!extMap.containsKey(dirName)) {
					json.setError_msg("目录名不正确。");
					return json;
				}
				// 创建文件夹
				String uid = UUID.randomUUID().toString().replaceAll("-", "");
				path = path+dirName + "/"+tempPath.toString()+uid+"/";
				url = url+dirName + "/"+tempPath.toString()+uid+"/";
				File saveDirFile = new File(path);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					json.setError_msg("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
					return json;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(path, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					json.setError_msg("上传文件失败。");
					return json;
				}
				
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", url + newFileName);
				//WebUtil.writerJson(response, msg);
				fileurl+=","+url + newFileName;
			}else{
				String name=item.getFieldName();
				String value=item.getString("UTF-8");
				//System.out.println(name);
				//System.out.println(value);
				if(!name.equals("handleUser")){
					params.put(name, value);
				}
				//request.setAttribute(name, value);
			}
		}
		if(!fileurl.equals("")){
			fileurl=fileurl.substring(1, fileurl.length());
		}
		json.setError_msg(fileurl);
		json.set_success(true);
		
		return json;
	}
	
}
