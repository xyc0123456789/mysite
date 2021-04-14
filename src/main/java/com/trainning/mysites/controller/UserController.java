package com.trainning.mysites.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trainning.mysites.domain.User;
import com.trainning.mysites.service.UserService;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
   @Autowired
   private UserService userService;

   @RequestMapping({"/listusers"})
   public String list(String kw, Model model, Pageable pageable) {
      model.addAttribute("kw", kw);
      if (kw != null) {
         kw = "%" + kw + "%";
      } else {
         kw = "%%";
      }

      Page page = this.userService.findAll(kw, pageable);
      model.addAttribute("pages", page);
      return "listusers";
   }

   @GetMapping({"/edituser", "/edituser/{uid}"})
   public String edit(@PathVariable(name = "uid",required = false) Integer uid, Model model) {
      User user = new User();
      if (uid != null && uid > 0) {
         user = this.userService.findById(uid);
      }

      model.addAttribute("user", user);
      model.addAttribute("sexes", User.Sex.toList());
      return "edituser";
   }

   @PostMapping({"/saveuser"})
   public String save(@Validated User user, BindingResult bindingResult, RedirectAttributes attr) {
      try {
         if (!bindingResult.hasErrors()) {
            this.userService.save(user);
            attr.addFlashAttribute("ok", "保存成功");
            return "redirect:/listusers";
         } else {
            List list = bindingResult.getAllErrors();
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
               ObjectError error = (ObjectError)var5.next();
               PrintStream var10000 = System.out;
               String var10001 = error.getCode();
               var10000.println(var10001 + "---" + error.getArguments() + "---" + error.getDefaultMessage());
            }

            return "edituser";
         }
      } catch (Exception var7) {
         return "edituser";
      }
   }

   @GetMapping({"/deleteuser/{uid}"})
   public String delete(@PathVariable("uid") Integer uid) {
      this.userService.deleteById(uid);
      return "redirect:/listusers";
   }

   @PostMapping({"/deleteusers"})
   public String deletes(String uids) {
      List users = new ArrayList();
      JSONObject json = JSONObject.parseObject(uids);
      JSONArray arr = json.getJSONArray("uids");
      int ilen = arr.size();

      for(int i = 0; i < ilen; ++i) {
         users.add(this.userService.findById(arr.getInteger(i)));
      }

      this.userService.deletes(users);
      return "redirect:/listusers";
   }

   @GetMapping({"/validuser/{uid}"})
   public String validuser(@PathVariable("uid") Integer uid) {
      User user = this.userService.findById(uid);
      user.setValidstate(1 - user.getValidstate());
      return "redirect:/listusers";
   }
}
