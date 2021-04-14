package com.trainning.mysites.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(
   name = "contenttype"
)
public class ContentType {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Integer tid;
   private String name;
   private Integer weight;
   @ManyToOne(
      cascade = {CascadeType.ALL}
   )
   @Nullable
   @JoinColumn(
      name = "pid"
   )
   @JsonIgnore
   private ContentType parent;
   @OneToMany(
      mappedBy = "parent"
   )
   private List<ContentType> sublists;

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ContentType)) {
         return false;
      } else {
         ContentType that = (ContentType)o;
         if (!this.getWeight().equals(that.getWeight())) {
            return false;
         } else {
            return this.getName().equals(that.getName());
         }
      }
   }

   public int hashCode() {
      int result = this.getTid().hashCode();
      result = 31 * result + this.getName().hashCode();
      result = 31 * result + this.getWeight().hashCode();
      return result;
   }

   public Integer getTid() {
      return this.tid;
   }

   public void setTid(Integer tid) {
      this.tid = tid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getWeight() {
      return this.weight;
   }

   public void setWeight(Integer weight) {
      this.weight = weight;
   }

   @Nullable
   public ContentType getParent() {
      return this.parent;
   }

   public void setParent(@Nullable ContentType parent) {
      this.parent = parent;
   }

   public List getSublists() {
      return this.sublists;
   }

   public void setSublists(List sublists) {
      this.sublists = sublists;
   }
}
