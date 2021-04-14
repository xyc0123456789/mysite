package com.trainning.mysites.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "sites"
)
public class Site {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Integer sid;
   private String title;
   private String skey;
   private String svalue;
   private boolean webvisiable;
   private Site.ValueType stype;

   public Integer getSid() {
      return this.sid;
   }

   public void setSid(Integer sid) {
      this.sid = sid;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSkey() {
      return this.skey;
   }

   public void setSkey(String skey) {
      this.skey = skey;
   }

   public String getSvalue() {
      return this.svalue;
   }

   public void setSvalue(String svalue) {
      this.svalue = svalue;
   }

   public boolean isWebvisiable() {
      return this.webvisiable;
   }

   public void setWebvisiable(boolean webvisiable) {
      this.webvisiable = webvisiable;
   }

   public Site.ValueType getStype() {
      return this.stype;
   }

   public void setStype(Site.ValueType stype) {
      this.stype = stype;
   }

   public static enum ValueType {
      TEXT,
      HTML,
      IMAGE,
      BIGTEXT;
   }
}
