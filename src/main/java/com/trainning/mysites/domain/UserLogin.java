package com.trainning.mysites.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLogin {
   @NotBlank
   @Size(
      min = 6,
      message = "账号至少6位"
   )
   private String account;
   @NotBlank
   @Size(
      min = 6,
      message = "密码至少6位"
   )
   private String password;

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
}
