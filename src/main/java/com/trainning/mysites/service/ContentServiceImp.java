package com.trainning.mysites.service;

import com.trainning.mysites.dao.ContentRepository;
import com.trainning.mysites.domain.Content;
import com.trainning.mysites.domain.Search;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImp implements ContentService {
   @Autowired
   private ContentRepository contentRepository;

   public void save(Content content) {
      this.contentRepository.save(content);
   }

   public void delete(Content content) {
      this.contentRepository.delete(content);
   }

   public void deleteById(Integer cid) {
      this.contentRepository.deleteById(cid);
   }

   @Transactional
   public void deletes(Iterable<Content> contents) {
      this.contentRepository.deleteAll(contents);
   }

   public Content findById(Integer cid) {
      Optional<Content> oc = this.contentRepository.findById(cid);
      return oc.orElse(null);
   }

   public Page<Content> findBySearch(Search search, Pageable pageable) {
      if (pageable.getSort().isUnsorted()) {
         pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("cid").descending());
      }

      return contentRepository.findAll(new Specification<Content>() {
      	@Override
         public Predicate toPredicate(Root<Content> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            Predicate p = criteriaBuilder.conjunction();
            Predicate b = criteriaBuilder.disjunction();
            if (search.getKeyword() != null && !"".equals(search.getKeyword())) {
               String kw = "%" + search.getKeyword() + "%";
               Predicate a1 = criteriaBuilder.like(root.get("title").as(String.class), kw);
               Predicate a2 = criteriaBuilder.like(root.get("contents").as(String.class), kw);
               b.getExpressions().add(a1);
               b.getExpressions().add(a2);
            }

            Predicate a1x;
            if (search.getSdate() != null) {
               a1x = criteriaBuilder.greaterThanOrEqualTo(root.get("cdate").as(LocalDate.class), search.getSdate());
               p.getExpressions().add(a1x);
            }

            if (search.getEdate() != null) {
               a1x = criteriaBuilder.lessThanOrEqualTo(root.get("cdate").as(LocalDate.class), search.getEdate());
               p.getExpressions().add(a1x);
            }

            if (b.getExpressions().size() > 0) {
               p.getExpressions().add(b);
            }

            return p;
         }
      }, pageable);
   }
}
