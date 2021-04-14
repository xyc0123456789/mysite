package com.baidu.ueditor.hunter;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class FileManager {
   private String dir = null;
   private String rootPath = null;
   private String[] allowFiles = null;
   private int count = 0;

   public FileManager(Map conf) {
      this.rootPath = (String)conf.get("rootPath");
      String var10001 = this.rootPath;
      this.dir = var10001 + (String)conf.get("dir");
      this.allowFiles = this.getAllowFiles(conf.get("allowFiles"));
      this.count = (Integer)conf.get("count");
   }

   public State listFile(int index) {
      File dir = new File(this.dir);
      State state = null;
      if (!dir.exists()) {
         return new BaseState(false, 302);
      } else if (!dir.isDirectory()) {
         return new BaseState(false, 301);
      } else {
         Collection list = FileUtils.listFiles(dir, this.allowFiles, true);
         if (index >= 0 && index <= list.size()) {
            Object[] fileList = Arrays.copyOfRange(list.toArray(), index, index + this.count);
            state = this.getState(fileList);
         } else {
            state = new MultiState(true);
         }

         ((State)state).putInfo("start", (long)index);
         ((State)state).putInfo("total", (long)list.size());
         return (State)state;
      }
   }

   private State getState(Object[] files) {
      MultiState state = new MultiState(true);
      BaseState fileState = null;
      File file = null;
      Object[] var5 = files;
      int var6 = files.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Object obj = var5[var7];
         if (obj == null) {
            break;
         }

         file = (File)obj;
         fileState = new BaseState(true);
         fileState.putInfo("url", PathFormat.format(this.getPath(file)));
         state.addState(fileState);
      }

      return state;
   }

   private String getPath(File file) {
      String path = file.getAbsolutePath();
      return path.replace(this.rootPath, "/");
   }

   private String[] getAllowFiles(Object fileExt) {
      String[] exts = null;
      String ext = null;
      if (fileExt == null) {
         return new String[0];
      } else {
         exts = (String[])fileExt;
         int i = 0;

         for(int len = exts.length; i < len; ++i) {
            ext = exts[i];
            exts[i] = ext.replace(".", "");
         }

         return exts;
      }
   }
}
