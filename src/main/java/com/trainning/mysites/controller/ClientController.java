package com.trainning.mysites.controller;

import com.trainning.mysites.domain.Content;
import com.trainning.mysites.service.ClientService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/c"})
public class ClientController {
   @Autowired
   private ClientService clientService;

   @GetMapping({"scroll"})
   @ResponseBody
   public List scroll(Model model) {
      return this.clientService.findByScroll();
   }

   @GetMapping({"slide"})
   @ResponseBody
   public List slide(Model model) {
      return this.clientService.findBySlide();
   }

   @GetMapping({"gg"})
   @ResponseBody
   public Content gg() {
      List datas = this.clientService.findByFlag(Content.ContentFlag.公告);
      return datas != null && datas.size() > 0 ? (Content)datas.get(0) : null;
   }

   @GetMapping({"con"})
   @ResponseBody
   public List con() {
      return this.clientService.findByFlag(Content.ContentFlag.首页内容);
   }

   @GetMapping({"site"})
   @ResponseBody
   public Map site() {
      return this.clientService.findBySite();
   }

   @GetMapping({"detail/{mname}/{cid}"})
   public String detail(@PathVariable("mname") String mname, @PathVariable("cid") Integer cid, Model model) {
      String var10002 = mname.substring(0, 1).toUpperCase();
      model.addAttribute("mname", var10002 + mname.substring(1));
      model.addAttribute("con", this.clientService.findById(cid));
      return "detail";
   }
}
