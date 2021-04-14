package com.trainning.mysites.service;

import com.trainning.mysites.dao.UserRepository;
import com.trainning.mysites.domain.User;
import com.trainning.mysites.domain.UserLogin;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {
   @Autowired
   private UserRepository userRepository;

   public void save(User u) throws Exception {
      try {
         u.setLasttime((int)Instant.now().getEpochSecond());
         this.userRepository.save(u);
      } catch (Exception var3) {
         throw var3;
      }
   }

   public Page<User> findAll(String kw, Pageable pageable) {
      return this.userRepository.findByKeyword(kw, pageable);
   }

   public User findById(Integer uid) {
   		Optional<User> ou = this.userRepository.findById(uid);
	   return ou.orElse(null);
   }

   public void delete(User u) {
      this.userRepository.delete(u);
   }

   public void deleteById(Integer uid) {
      this.userRepository.deleteById(uid);
   }

   @Transactional
   public void deletes(List<User> users) {

	   for (User user : users) {
		   this.userRepository.delete(user);
	   }

   }

   public User checkUser(UserLogin user) {
      User u = null;
      Optional<User> ou = this.userRepository.findByAccount(user.getAccount());
      if (ou.isPresent()) {
         u = ou.get();
         if (u.getPassword().equals(user.getPassword())) {
            return u;
         }
      }

      return null;
   }
}
