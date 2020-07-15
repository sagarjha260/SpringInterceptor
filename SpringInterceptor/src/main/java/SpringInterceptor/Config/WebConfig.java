package SpringInterceptor.Config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import SpringInterceptor.interceptor.AdminInterceptor;
import SpringInterceptor.interceptor.GuestInterceptor;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "SpringInterceptor.*" })
public class WebConfig extends WebMvcConfigurerAdapter {

   @Override
   public void addInterceptors(InterceptorRegistry registry) 
   {
      //Register guest interceptor with single path pattern
      registry.addInterceptor(new GuestInterceptor()).addPathPatterns("/guest");

      //Register admin interceptor with multiple path patterns
      registry.addInterceptor(new AdminInterceptor()).addPathPatterns(new String[] { "/admin", "/admin/*" });
   }
   
   @Bean
   public InternalResourceViewResolver resolver() 
   {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }
}