package cn.cyit.traffic.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;


public class WebUtil {
	private static int BUFFER_SIZE = 8096;
	/**
     * 计算要生成的静态文件相对路径
     * 因为大家在调试的时候一般在Tomcat的webapps下面新建站点目录的，
     * 但在实际应用时直接布署到ROOT目录里面,这里要保证路径的一致性。
     * @param request HttpServletRequest
     * @return /目录/*.htm
     */
	 public static String getRequestHTML(HttpServletRequest request){
        //web应用名称,部署在ROOT目录时为空
        String contextPath = request.getContextPath();
        //web应用/目录/文件.do
        String requestURI = request.getRequestURI();
        //basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
        requestURI = requestURI.replaceFirst(contextPath, "");
        String lastPathOrFile=requestURI.substring(requestURI.lastIndexOf('/'));
        if(lastPathOrFile.indexOf('.')>0){
        requestURI = requestURI.substring(0, requestURI.lastIndexOf(".")) + ".htm";
        }else{
        	if(lastPathOrFile.length()>1){
        	requestURI = requestURI + "/index.htm";
        	}else{
        		requestURI = requestURI + "index.htm";
        	}
        }
         
        return requestURI;
    }
	 public static String getRealPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	 public static boolean isFile(HttpServletRequest request){
		return request.getRequestURL().toString().matches(".*(htm|js|html|css)");
	}
	
	 public static boolean isNoParam(HttpServletRequest request){
		return "GET".equals(request.getMethod().toUpperCase())&&(null==request.getQueryString()||"".equals(request.getQueryString()));
	}
	 
	 public static Map JsonHttpsClient(String httpsURL){
			    return JsonUtil.toObject(getHttpGetResult(httpsURL), HashMap.class);
	 }
	 
	 public static String getHttpGetResult(String url){
		 URL myurl=null;
		 URLConnection con=null;
		 InputStream ins =null;
		 InputStreamReader isr =null;
		 BufferedReader in =null;
		 String charset="UTF-8";
		 StringBuffer sb=new StringBuffer();
		 String line;
		 try{
			    myurl = new URL(url);
			    con = myurl.openConnection();
			    String contentType=con.getContentType();
			    if(null!=contentType&&contentType.indexOf("charset=")>0){
			    charset=contentType.substring(contentType.indexOf("charset=")+8).toUpperCase();
			    }
			    ins = con.getInputStream();
			    isr = new InputStreamReader(ins,charset);
			    in = new BufferedReader(isr);
			    while ((line = in.readLine()) != null)
			    {
			    	sb.append(line);
			    }
			    return sb.toString();
			 }catch(Exception e){
				 return null;
			 }
			 finally{
				 if(null!=con){
					 if(con instanceof HttpURLConnection){
						 ((HttpURLConnection)con).disconnect();
					 }else if(con instanceof HttpsURLConnection){
						 ((HttpsURLConnection)con).disconnect();
					 }
					 con=null;
				 }
				 if(null!=in){
					 try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					 in=null;
				 }
			 }
	 }
	 public static String getHttpGetResult(String urlstr,String content){
		 OutputStream out = null;
			BufferedReader bufferedReader = null;
			HttpURLConnection httpURLConnection = null;
			StringBuffer responseResult = new StringBuffer();
		 try {
			 URL url = new URL(urlstr);
				httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestProperty("accept", "*/*");
				httpURLConnection.setRequestProperty("connection", "Keep-Alive");
				httpURLConnection.setRequestProperty("Content-Length", content
						.getBytes("UTF-8").length
						+ "");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				out = httpURLConnection.getOutputStream();
				out.write(content.getBytes("UTF-8"));
				out.flush();
				int responseCode = httpURLConnection.getResponseCode();
				bufferedReader = new BufferedReader(new InputStreamReader(
						httpURLConnection.getInputStream(), "UTF-8"));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					responseResult.append(line);
				}
				if (responseCode != 200) {
					return null;
				}
			} catch (Exception e) {
			} finally {
				httpURLConnection.disconnect();
				try {
					if (out != null) {
						out.close();
					}
					if (bufferedReader != null) {
						bufferedReader.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return responseResult.toString();
	 }
	 
	 public static byte[] getHttpGetStream(String url){
		 byte[] buffer = new byte[BUFFER_SIZE];
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 URL myurl=null;
		 URLConnection con=null;
		 BufferedInputStream bis = null;
		 try{
			    myurl = new URL(url);
			    con = myurl.openConnection();
			    bis = new BufferedInputStream(con.getInputStream());
			    int len = 0;  
	            while(-1 != (len = bis.read(buffer,0,BUFFER_SIZE))){  
	                bos.write(buffer,0,len);  
	            }  
	            return bos.toByteArray();  
			 }catch(Exception e){
				 return null;
			 }
			 finally{
				 if(null!=con){
					 if(con instanceof HttpURLConnection){
						 ((HttpURLConnection)con).disconnect();
					 }else if(con instanceof HttpsURLConnection){
						 ((HttpsURLConnection)con).disconnect();
					 }
				 }
				 if(null!=bis){
					 try {
						 bis.close();
						 bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
			 }
	 }
}
