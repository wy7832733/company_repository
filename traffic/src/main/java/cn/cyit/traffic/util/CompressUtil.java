package cn.cyit.traffic.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cn.cyit.traffic.bean.Attachment;

public class CompressUtil {
	  
    public static void main(String[] args) throws Exception {
    	File file=new File("C:\\Users\\Administrator\\Desktop\\证书尺寸.zip");
    	unZip(file, "C:\\Users\\Administrator\\Desktop\\111",null);
    } 
    
	public static List<Attachment> unZip(File srcFile, String destDirPath,String path2) throws RuntimeException {
		List<Attachment> result=new ArrayList<>();
		long start = System.currentTimeMillis();
	    	// 判断源文件是否存在
	    	if (!srcFile.exists()) {
	    		throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
	    	}
	    	// 开始解压
	    	ZipFile zipFile = null;
	    	try {
	    		zipFile = new ZipFile(srcFile);
	    		Enumeration<?> entries = zipFile.entries();
	    		while (entries.hasMoreElements()) {
	    			ZipEntry entry = (ZipEntry) entries.nextElement();
	    			System.out.println("解压" + entry.getName());
	    			// 如果是文件夹，就创建个文件夹
	    			if (entry.isDirectory()) {
	    				String dirPath = destDirPath + "/" + entry.getName();
	    				File dir = new File(dirPath);
	    				dir.mkdirs();
	    			} else {
	    				// 如果是文件，就先创建一个文件，然后用io流把内容copy过去
	    				File targetFile = new File(destDirPath + "/" + entry.getName());
	    				// 保证这个文件的父文件夹必须要存在
	    				if(!targetFile.getParentFile().exists()){
	    					targetFile.getParentFile().mkdirs();
	    				}
	    				targetFile.createNewFile();
	    				// 将压缩文件内容写入到这个文件中
	    				InputStream is = zipFile.getInputStream(entry);
	    				FileOutputStream fos = new FileOutputStream(targetFile);
	    				int len;
	    				byte[] buf = new byte[1024];
	    				while ((len = is.read(buf)) != -1) {
	    					fos.write(buf, 0, len);
	    				}
	    				// 关流顺序，先打开的后关闭
	    				fos.close();
	    				is.close();
	    				
	    				Attachment att=new Attachment();
	    				att.setReal_Path(path2+ entry.getName());
	    				att.setAttach_type(entry.getName().substring(entry.getName().indexOf(".")+1));
	    				result.add(att);
	    			}
	    			
	    		}
	    		long end = System.currentTimeMillis();
	    		System.out.println("解压完成，耗时：" + (end - start) +" ms");
	    	} catch (Exception e) {
	    		throw new RuntimeException("unzip error from ZipUtils", e);
	    	} finally {
	    		if(zipFile != null){
	    			try {
	    				zipFile.close();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	}
	    	return result;
	    }   
	    
}
