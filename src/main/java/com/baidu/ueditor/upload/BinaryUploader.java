package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class BinaryUploader {
   public static final State save(HttpServletRequest request, Map conf) {
      boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
      if (request instanceof MultipartHttpServletRequest) {
         MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;

         try {
            if (isAjaxUpload) {
               mr.setCharacterEncoding("UTF-8");
            }

            if (mr.getMultiFileMap().size() <= 0) {
               return new BaseState(false, 7);
            }

            Iterator iterator = mr.getFileMap().values().iterator();
            if (iterator.hasNext()) {
               MultipartFile file = (MultipartFile)iterator.next();
               String savePath = (String)conf.get("savePath");
               String originFileName = file.getOriginalFilename();
               String suffix = FileType.getSuffixByFilename(originFileName);
               originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
               savePath = savePath + suffix;
               long maxSize = (Long)conf.get("maxSize");
               if (!validType(suffix, (String[])conf.get("allowFiles"))) {
                  return new BaseState(false, 8);
               }

               savePath = PathFormat.parse(savePath, originFileName);
               String var10000 = (String)conf.get("rootPath");
               String physicalPath = var10000 + savePath;
               InputStream is = file.getInputStream();
               State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
               is.close();
               if (storageState.isSuccess()) {
                  storageState.putInfo("url", PathFormat.format(savePath));
                  storageState.putInfo("type", suffix);
                  storageState.putInfo("original", originFileName + suffix);
               }

               return storageState;
            }
         } catch (Exception var14) {
            var14.printStackTrace();
         }
      }

      return new BaseState(false, 4);
   }

   private static boolean validType(String type, String[] allowTypes) {
      List list = Arrays.asList(allowTypes);
      return list.contains(type);
   }
}
