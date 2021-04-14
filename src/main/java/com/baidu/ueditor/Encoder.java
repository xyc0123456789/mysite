package com.baidu.ueditor;

public class Encoder {
   public static String toUnicode(String input) {
      StringBuilder builder = new StringBuilder();
      char[] chars = input.toCharArray();
      char[] var3 = chars;
      int var4 = chars.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char ch = var3[var5];
         if (ch < 256) {
            builder.append(ch);
         } else {
            builder.append("\\u" + Integer.toHexString(ch & '\uffff'));
         }
      }

      return builder.toString();
   }
}
