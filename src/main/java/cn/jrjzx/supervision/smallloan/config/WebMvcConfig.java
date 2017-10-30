package cn.jrjzx.supervision.smallloan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.viewResolver.ExcelViewResolver;
import cn.jrjzx.supervision.smallloan.viewResolver.PdfViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  @Autowired 
  HandlerInterceptor sessionIntercepTor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(sessionIntercepTor).excludePathPatterns(Constant.PASS_PATHS);
  }
  
  

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer
            .defaultContentType(MediaType.APPLICATION_JSON)
            .favorPathExtension(true);
}


/*
 * Configure View resolver to provide XLS output using Apache POI library to
 * generate XLS output for an object content
 */
@Bean
public ViewResolver excelViewResolver() {
    return new ExcelViewResolver();
}

@Bean
public ViewResolver pdfViewResolver() {
    return new PdfViewResolver();
}


}