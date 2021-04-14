package com.trainning.mysites.service;

import com.trainning.mysites.domain.User;
import com.trainning.mysites.domain.UserLogin;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
   void save(User u) throws Exception;

   Page<User> findAll(String kw, Pageable pageable);

   User findById(Integer uid);

   void delete(User u);

   void deleteById(Integer uid);

   void deletes(List<User> users);

   User checkUser(UserLogin user);
}
