package com.trainning.mysites.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
   name = "users"
)
public class User {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Integer uid;
   @Column(
      length = 30,
      unique = true
   )
   @NotBlank(
      message = "账号不能为空"
   )
   @Size(
      min = 6,
      message = "账号至少6位"
   )
   private String account;
   @Column(
      length = 30
   )
   @NotBlank(
      message = "密码不能为空"
   )
   @Size(
      min = 6,
      message = "密码至少6位"
   )
   private String password;
   @Column(
      length = 30
   )
   private String name;
   private User.Sex grander;
   private LocalDate birthday;
   @Column(
      length = 11
   )
   private String mobile;
   @Column(
      length = 100
   )
   private String email;
   private Integer lasttime;
   private Integer logincount;
   private Integer validstate = 1;

   public Integer getUid() {
      return this.uid;
   }

   public void setUid(Integer uid) {
      this.uid = uid;
   }

   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public User.Sex getGrander() {
      return this.grander;
   }

   public void setGrander(User.Sex grander) {
      this.grander = grander;
   }

   public LocalDate getBirthday() {
      return this.birthday;
   }

   public void setBirthday(LocalDate birthday) {
      this.birthday = birthday;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Integer getLasttime() {
      return this.lasttime;
   }

   public void setLasttime(Integer lasttime) {
      this.lasttime = lasttime;
   }

   public Integer getLogincount() {
      return this.logincount;
   }

   public void setLogincount(Integer logincount) {
      this.logincount = logincount;
   }

   public Integer getValidstate() {
      return this.validstate;
   }

   public void setValidstate(Integer validstate) {
      this.validstate = validstate;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof User)) {
         return false;
      } else {
         User user = (User)o;
         return this.getUid().equals(user.getUid()) && this.getAccount().equals(user.getAccount());
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.getUid(), this.getAccount()});
   }

   public static enum Sex {
      男,
      女;

      public static List toList() {
         User.Sex[] sex = values();
         List datas = new ArrayList();
         User.Sex[] var2 = sex;
         int var3 = sex.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            User.Sex s = var2[var4];
            datas.add(s.name());
         }

         return datas;
      }
   }
}
