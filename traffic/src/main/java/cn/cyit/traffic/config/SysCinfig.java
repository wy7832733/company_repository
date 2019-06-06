package cn.cyit.traffic.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class SysCinfig {
	
	public static String dbType;
    public static String attach_path;
    public static String img_m_path;
    public static String img_s_path;
    
	    @Value("#{sys.dbType}")  
	    public void setTest(String dbType){  
	    	dbType = dbType;  
	    }  
	  
	    @Value("#{sys}")  
	    public void setSysConf(Properties sys){  
	    	dbType= sys.getProperty("dbType");  
	    }  
	    
	    @Value("#{sys.attach_path}")
	    public void setAttach_path(String attach_path) {
	    	SysCinfig.attach_path = attach_path;
	    }
	        
}
