package cn.cyit.traffic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.cyit.traffic.bean.User;

public class RequestInterception implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
            //说明处在编辑的页面  
            HttpSession session = request.getSession();  
            User user = (User) session.getAttribute("user");  
            if(user!=null){  
                //登陆成功的用户  
            	System.out.println("------------------------------------------------session正常---------------------------------------------");
                return true;  
            }else{  
               //没有登陆，转向登陆界面  
                //request.getRequestDispatcher("../view/Login.jsp").forward(request,response); 
    			response.sendRedirect(request.getContextPath() + "/login?message=overTime");
    			return false;              
            }    
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }	
	
}
