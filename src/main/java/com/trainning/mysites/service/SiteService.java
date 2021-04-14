package com.trainning.mysites.service;

import com.trainning.mysites.domain.Site;
import java.util.List;

public interface SiteService {
   void save(Site site);

   List<Site> findAll();
}
