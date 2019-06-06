package cn.cyit.traffic.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class MyExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
				//如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
				if(ex instanceof UnauthorizedException){
					System.out.println("==============无权限=============");
					ModelAndView mv = new ModelAndView("login");
					return mv;
				}
				ex.printStackTrace();
				ModelAndView mv = new ModelAndView("login");
				
				Logger loger = Logger.getLogger(MyExceptionResolver.class);
				loger.error(ex.getMessage(), ex);
				 
				mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
				return mv;

	}

}
