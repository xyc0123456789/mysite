package com.trainning.mysites.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
   name = "contents"
)
public class Content {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Integer cid;
   @NotBlank
   private String title;
   private String timg;
   @Lob
   private String contents;
   @ManyToOne
   @JoinColumn(
      name = "tid"
   )
   private ContentType type;
   private Integer visitcount;
   private LocalDateTime lastmodify;
   private Content.ContentFlag topflag;
   @ManyToOne
   @JoinColumn(
      name = "uid"
   )
   private User user;
   private LocalDateTime cdate;
   private Integer weight;

   public Integer getCid() {
      return this.cid;
   }

   public void setCid(Integer cid) {
      this.cid = cid;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTimg() {
      return this.timg;
   }

   public void setTimg(String timg) {
      this.timg = timg;
   }

   public String getContents() {
      return this.contents;
   }

   public void setContents(String contents) {
      this.contents = contents;
   }

   public ContentType getType() {
      return this.type;
   }

   public void setType(ContentType type) {
      this.type = type;
   }

   public Integer getVisitcount() {
      return this.visitcount;
   }

   public void setVisitcount(Integer visitcount) {
      this.visitcount = visitcount;
   }

   public LocalDateTime getLastmodify() {
      return this.lastmodify;
   }

   public void setLastmodify(LocalDateTime lastmodify) {
      this.lastmodify = lastmodify;
   }

   public Content.ContentFlag getTopflag() {
      return this.topflag;
   }

   public void setTopflag(Content.ContentFlag topflag) {
      this.topflag = topflag;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public LocalDateTime getCdate() {
      return this.cdate;
   }

   public void setCdate(LocalDateTime cdate) {
      this.cdate = cdate;
   }

   public Integer getWeight() {
      return this.weight;
   }

   public void setWeight(Integer weight) {
      this.weight = weight;
   }

   public String toString() {
      return "Content{cid=" + this.cid + ", title='" + this.title + "', timg='" + this.timg + "', contents='" + this.contents + "', type=" + this.type + ", visitcount=" + this.visitcount + ", lastmodify=" + this.lastmodify + ", topflag=" + this.topflag + ", user=" + this.user + ", cdate=" + this.cdate + ", weight=" + this.weight + "}";
   }

   public static enum ContentFlag {
      幻灯片,
      滚动,
      置顶,
      首页内容,
      公告;

      public static List toList() {
         Content.ContentFlag[] cf = values();
         return Arrays.asList(cf);
      }
   }
}
