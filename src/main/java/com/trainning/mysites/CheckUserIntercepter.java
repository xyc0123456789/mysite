package com.trainning.mysites;

import com.trainning.mysites.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class CheckUserIntercepter implements HandlerInterceptor {
   String[] res = new String[]{".js", ".css", ".html", "/error", ".jpg", ".png", ".gif", ".jpeg"};
   String[] res2 = new String[]{"/c/"};

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String path = request.getRequestURI();
      if (path.equals("/login")) {
         return HandlerInterceptor.super.preHandle(request, response, handler);
      } else {
         String[] var5 = this.res;
         int var6 = var5.length;

         int var7;
         String s;
         for(var7 = 0; var7 < var6; ++var7) {
            s = var5[var7];
            if (path.endsWith(s)) {
               return HandlerInterceptor.super.preHandle(request, response, handler);
            }
         }

         var5 = this.res2;
         var6 = var5.length;

         for(var7 = 0; var7 < var6; ++var7) {
            s = var5[var7];
            if (path.startsWith(s)) {
               return HandlerInterceptor.super.preHandle(request, response, handler);
            }
         }

         HttpSession session = request.getSession();
         User user = (User)session.getAttribute("user");
         if (user != null && user.getUid() != null && user.getUid() > 0) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
         } else {
            response.sendRedirect("/login");
            return false;
         }
      }
   }
}
