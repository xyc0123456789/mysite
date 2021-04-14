package com.trainning.mysites.domain;

import java.time.LocalDate;

public class Search {
   private String keyword;
   private LocalDate sdate;
   private LocalDate edate;

   public String getKeyword() {
      return this.keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public LocalDate getSdate() {
      return this.sdate;
   }

   public void setSdate(LocalDate sdate) {
      this.sdate = sdate;
   }

   public LocalDate getEdate() {
      return this.edate;
   }

   public void setEdate(LocalDate edate) {
      this.edate = edate;
   }

   public String toString() {
      return "Search{keyword='" + this.keyword + "', sdate=" + this.sdate + ", edate=" + this.edate + "}";
   }
}
