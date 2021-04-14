package com.trainning.mysites.service;

import com.trainning.mysites.dao.ContentRepository;
import com.trainning.mysites.dao.SiteRepository;
import com.trainning.mysites.domain.Content;
import com.trainning.mysites.domain.Site;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements ClientService {
   @Autowired
   private ContentRepository contentRepository;
   @Autowired
   private SiteRepository siteRepository;

   public List<Content> findByScroll() {
      return this.contentRepository.findByTopflag(Content.ContentFlag.滚动);
   }

   public List<Content> findBySlide() {
      return this.contentRepository.findByTopflag(Content.ContentFlag.幻灯片);
   }

   public List<Content> findByFlag(Content.ContentFlag flag) {
      return this.contentRepository.findByTopflag(flag);
   }

   public Map<String,Site> findBySite() {
      List<Site> sites = this.siteRepository.findAll();
      Map<String,Site> m = new HashMap<>();

	   for (Site site : sites) {
		   m.put(site.getSkey(), site);
	   }

      return m;
   }

   public Content findById(Integer id) {
      Optional<Content> oc = this.contentRepository.findById(id);
      return (Content) oc.orElse(null);
   }
}
