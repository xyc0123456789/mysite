package com.trainning.mysites.controller;

import com.trainning.mysites.domain.User;
import com.trainning.mysites.domain.UserLogin;
import com.trainning.mysites.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
   @Autowired
   private UserService userService;

   @GetMapping({"/login"})
   public String login(Model model) {
      model.addAttribute("userLogin", new UserLogin());
      return "login";
   }

   @PostMapping({"/login"})
   public String login(@Valid UserLogin user, BindingResult bindingResult, HttpSession session, Model model) {
      if (bindingResult.hasErrors()) {
         return "login";
      } else {
         User u = this.userService.checkUser(user);
         if (u != null) {
            session.setAttribute("user", u);
            return "index";
         } else {
            model.addAttribute("fail", "账号或密码不正确");
            return "redirect:/login";
         }
      }
   }
}
