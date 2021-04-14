package com.trainning.mysites.dao;

import java.util.Optional;

import com.trainning.mysites.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
   @Query("select u from User u where account like ?1 or name like ?1 or mobile like ?1 or email like ?1")
   Page<User> findByKeyword(String kw, Pageable pageable);

   @Query("update User u set u.password=?1 where u.uid=?2")
   void modifyPassword(String pwd, Integer uid);

   Optional<User> findByAccount(String acccount);
}
