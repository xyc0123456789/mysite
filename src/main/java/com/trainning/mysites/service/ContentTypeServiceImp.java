package com.trainning.mysites.service;

import com.trainning.mysites.dao.ContentTypeRepository;
import com.trainning.mysites.domain.ContentType;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentTypeServiceImp implements ContentTypeService {
   @Autowired
   private ContentTypeRepository contentTypeRepository;

   public void save(ContentType type) {
      this.contentTypeRepository.save(type);
   }

   public void delete(ContentType type) {
      this.contentTypeRepository.delete(type);
   }

   @Transactional
   public void deletes(Iterable<ContentType> types) {
      this.contentTypeRepository.deleteAll(types);
   }

   public void deleteById(Integer id) {
      this.contentTypeRepository.deleteById(id);
   }

   public List<ContentType> findByParent(ContentType parent) {
      return this.contentTypeRepository.findByParent(parent);
   }

   public List<ContentType> findAll() {
      return this.contentTypeRepository.findAll();
   }

   public ContentType findById(Integer id) {
      Optional<ContentType> oct = this.contentTypeRepository.findById(id);
      return oct.orElse(null);
   }
}
