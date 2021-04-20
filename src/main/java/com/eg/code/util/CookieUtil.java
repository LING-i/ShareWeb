package com.eg.code.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 完善用户记住密码功能
 */
public class CookieUtil {

    /**
     * 将用户名和密码构建cookie，并设置
     * @param response
     * @param userName
     * @param password
     * @param maxAge
     */
  public static void setCookie(HttpServletResponse response,String userName,String password,int maxAge){

      //1.构建cookie
      Cookie cookie = new Cookie("user",userName+"-"+password);
      //2.设置参数
      cookie.setPath("/");
      cookie.setMaxAge(maxAge);
      //3.写到请求中
      response.addCookie(cookie);
  }







}
