package com.trainning.mysites.dao;

import com.trainning.mysites.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site,Integer> {
}
