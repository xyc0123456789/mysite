package com.baidu.ueditor.define;

public interface State {
   boolean isSuccess();

   void putInfo(String name, String val);

   void putInfo(String name, long val);

   String toJSONString();
}
