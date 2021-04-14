package com.trainning.mysites.service;

import com.trainning.mysites.domain.Content;
import com.trainning.mysites.domain.Site;

import java.util.List;
import java.util.Map;

public interface ClientService {
   List<Content> findByScroll();

   List<Content> findBySlide();

   List<Content> findByFlag(Content.ContentFlag flag);

   Map<String, Site> findBySite();

   Content findById(Integer id);
}
