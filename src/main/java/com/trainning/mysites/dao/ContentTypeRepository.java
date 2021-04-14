package com.trainning.mysites.dao;

import com.trainning.mysites.domain.ContentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTypeRepository extends JpaRepository<ContentType,Integer> {
   List<ContentType> findByParent(ContentType parent);
}
