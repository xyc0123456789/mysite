package com.trainning.mysites.controller;

import com.trainning.mysites.domain.ContentType;
import com.trainning.mysites.service.ContentTypeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentTypeController {
   @Autowired
   private ContentTypeService contentTypeService;

   @GetMapping({"/edittype", "/edittype/{tid}"})
   public String edit(@PathVariable(name = "tid",required = false) Integer tid, Model model) {
      ContentType type = null;
      if (tid == null) {
         type = new ContentType();
      } else {
         type = this.contentTypeService.findById(tid);
      }

      model.addAttribute("parents", this.contentTypeService.findByParent((ContentType)null));
      model.addAttribute("contentType", type);
      return "edittype";
   }

   @PostMapping({"/savetype"})
   public String save(@Valid ContentType type, BindingResult bindingResult, Model model) {
      model.addAttribute("contentType", type);
      if (bindingResult.hasErrors()) {
      }

      if (type.getParent().getTid() == null) {
         type.setParent((ContentType)null);
      }

      this.contentTypeService.save(type);
      model.addAttribute("ok", "保存成功");
      return "redirect:/edittype/" + type.getTid();
   }

   @GetMapping({"/deletetype/{tid}"})
   public String delete(@PathVariable("tid") Integer tid, Model model) {
      this.contentTypeService.deleteById(tid);
      model.addAttribute("ok", "删除成功!");
      return "redirect:/edittype";
   }
}
