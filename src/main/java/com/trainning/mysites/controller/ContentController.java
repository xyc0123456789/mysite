package com.trainning.mysites.controller;

import com.trainning.mysites.domain.Content;
import com.trainning.mysites.domain.ContentType;
import com.trainning.mysites.domain.Search;
import com.trainning.mysites.service.ContentService;
import com.trainning.mysites.service.ContentTypeService;
import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContentController {
   @Autowired
   private ContentService contentService;
   @Autowired
   private ContentTypeService contentTypeService;

   @RequestMapping({"/listcontents"})
   public String list(Search search, Model model, Pageable pageable) {
      model.addAttribute("search", search);
      Page p = this.contentService.findBySearch(search, pageable);
      model.addAttribute("pages", p);
      return "listcontents";
   }

   @GetMapping({"/editcontent", "/editcontent/{cid}"})
   public String edit(@PathVariable(name = "cid",required = false) Integer cid, Model model) {
      Content c = null;
      if (cid == null) {
         c = new Content();
      } else {
         c = this.contentService.findById(cid);
      }

      model.addAttribute("types", this.contentTypeService.findByParent((ContentType)null));
      model.addAttribute("flags", Content.ContentFlag.toList());
      model.addAttribute("content", c);
      return "editcontent";
   }

   @PostMapping({"/savecontent"})
   public String save(@Valid Content content, BindingResult bindingResult, Model model, RedirectAttributes attr) {
      if (bindingResult.hasErrors()) {
         model.addAttribute("fail", "信息有误");
         return "editcontent";
      } else {
         content.setCdate(LocalDateTime.now());
         this.contentService.save(content);
         attr.addFlashAttribute("ok", "保存成功");
         return "redirect:/editcontent/" + content.getCid();
      }
   }
}
