package com.trainning.mysites.service;

import com.trainning.mysites.dao.SiteRepository;
import com.trainning.mysites.domain.Site;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImp implements SiteService {
   @Autowired
   private SiteRepository siteRepository;

   public void save(Site site) {
      this.siteRepository.save(site);
   }

   public List<Site> findAll() {
      return this.siteRepository.findAll();
   }
}
