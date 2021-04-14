package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;

public class FileType {
   public static final String JPG = "JPG";
   private static final Map types = new HashMap() {
      {
         this.put("JPG", ".jpg");
      }
   };

   public static String getSuffix(String key) {
      return (String)types.get(key);
   }

   public static String getSuffixByFilename(String filename) {
      return filename.substring(filename.lastIndexOf(".")).toLowerCase();
   }
}
