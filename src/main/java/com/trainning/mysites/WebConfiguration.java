package com.trainning.mysites;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
   @Bean
   public CheckUserIntercepter checkUserIntercepter() {
      return new CheckUserIntercepter();
   }

   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(this.checkUserIntercepter());
   }

   public void addFormatters(FormatterRegistry registry) {
      registry.addFormatter(new Formatter<LocalDate>() {
         public String print(LocalDate localDate, Locale locale) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);
            return dtf.format(localDate);
         }

         public LocalDate parse(String s, Locale locale) throws ParseException {
            return LocalDate.parse(s);
         }
      });
   }

   @Bean
   public Utils utils() {
      return new Utils();
   }
}
