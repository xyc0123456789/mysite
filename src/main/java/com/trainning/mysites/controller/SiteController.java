package com.trainning.mysites.controller;

import com.trainning.mysites.domain.Site;
import com.trainning.mysites.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SiteController {
   @Autowired
   private SiteService siteService;

   @GetMapping({"/editsite"})
   public String edit(Model model) {
      model.addAttribute("sites", this.siteService.findAll());
      return "editsite";
   }

   @PostMapping({"/savesite"})
   public String save(Site s, RedirectAttributes attr) {
      this.siteService.save(s);
      attr.addFlashAttribute("ok", "保存成功！");
      return "redirect:/editsite";
   }
}
