package com.trainning.mysites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
   public String numstr(String s, int num) {
      StringBuilder res = new StringBuilder();

      for(int i = 0; i < num; ++i) {
         res.append(s);
      }

      return res.toString();
   }

   public String itod(Integer idate) {
      if (idate == null) {
         return "";
      } else {
         Date date = new Date();
         date.setTime((long)idate * 1000L);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return sdf.format(date);
      }
   }
}
