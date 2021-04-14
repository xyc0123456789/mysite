package com.trainning.mysites.controller;

import com.baidu.ueditor.ActionEnter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ue"})
@PropertySource({"application.properties"})
public class UEditController {
   @Value("${upload.path}")
   private String upath;

   @RequestMapping({"/editor"})
   @ResponseBody
   public String editor(HttpServletRequest request, HttpServletResponse response) {
      response.setHeader("Content-Type", "text/html");
      String rootPath = this.upath;
      return (new ActionEnter(request, rootPath)).exec();
   }
}
