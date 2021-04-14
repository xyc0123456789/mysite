package com.trainning.mysites.service;

import com.trainning.mysites.domain.Content;
import com.trainning.mysites.domain.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentService {
   void save(Content content);

   void delete(Content content);

   void deleteById(Integer cid);

   void deletes(Iterable<Content> contents);

   Content findById(Integer cid);

   Page<Content> findBySearch(Search search, Pageable pageable);
}
