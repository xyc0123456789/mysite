package com.trainning.mysites.dao;

import com.trainning.mysites.domain.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContentRepository extends JpaRepository<Content,Integer>, JpaSpecificationExecutor<Content> {
   List<Content> findByTopflag(Content.ContentFlag topflag);
}
