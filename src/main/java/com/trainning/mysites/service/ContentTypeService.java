package com.trainning.mysites.service;

import com.trainning.mysites.domain.ContentType;
import java.util.List;

public interface ContentTypeService {
   void save(ContentType type);

   void delete(ContentType type);

   void deletes(Iterable<ContentType> types);

   void deleteById(Integer id);

   List<ContentType> findByParent(ContentType parent);

   List<ContentType> findAll();

   ContentType findById(Integer id);
}
