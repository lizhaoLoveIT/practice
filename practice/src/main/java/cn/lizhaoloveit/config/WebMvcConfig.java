package cn.lizhaoloveit.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 * Author: Ammar
 * Date:   2020-04-16
 * Time:   10:39
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.TEXT_HTML)
//        converter.setSupportedMediaTypes(mediaTypes);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(jacksonObjectMapper(new Jackson2ObjectMapperBuilder()));
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_XML);
        mediaTypes.add(MediaType.MULTIPART_FORM_DATA);
        mediaTypes.add(MediaType.IMAGE_JPEG);
        mediaTypes.add(MediaType.IMAGE_PNG);
        converter.setSupportedMediaTypes(mediaTypes);
        converters.add(converter);
        converters.add(mappingJackson2HttpMessageConverter);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // // 字段保留，将null值转为""
        // objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        //     @Override
        //     public void serialize(Object o, JsonGenerator jsonGenerator,SerializerProvider serializerProvider)
        //             throws IOException, JsonProcessingException {
        //         jsonGenerator.writeString("");
        //     }
        // });
        return objectMapper;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }

//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**") 	// 对哪些资源起过滤作用
//                .excludePathPatterns("/..");// 对哪些资源起排除作用
//    }


    /**
     视图控制
     以前写SpringMVC的时候，如果需要访问一个页面，必须要写Controller类，
     然后再写一个方法跳转到页面，感觉好麻烦，
     其实重写WebMvcConfigurer中的addViewControllers方法即可达到效果了
     */
//    public void addViewControllers(
//            ViewControllerRegistry registry) {
//        //aaa请求会映射到aaa界面
//        registry.addViewController("/aaa").setViewName("aaa");
//    }

    /**
     * 比如，我们想自定义静态资源映射目录的话，只需重写addResourceHandlers方法即可。
     * 注：如果继承WebMvcConfigurationSupport类实现配置时必须要重写该方法
     * 将所有/static/** 访问都映射到classpath:/static/ classpath:static/ 目录下
     * file:/Users/Ammar/Desktop/work/static
     */


//    @Override
//    public void addArgumentResolvers(
//            List<HandlerMethodArgumentResolver> resolvers) {
//
//    }

    /**
     * 添加跨域映射
     **/
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE"); // 整个应用都支持跨域映射
//    }

//    registry.addMapping("/api/**")
//            .allowedOrigins("http://domain2.com")
//                .allowedMethods("PUT", "DELETE")
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);

    /* 配置视图解析器 */
//    @Override
//    public void configureViewResolvers(
//            ViewResolverRegistry registry) {
//
//    }


    // FreeMarkerResolver

//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setCache(true);
////        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setExposeSessionAttributes(true);
//        return resolver;
//    }
//
//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
//        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
//        factory.setTemplateLoaderPaths("classpath:templates", "src/main/resources/templates");
//        factory.setDefaultEncoding("UTF-8");
//        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
//        freemarker.template.Configuration configuration = factory.createConfiguration();
//        configuration.setClassicCompatible(true);
//        result.setConfiguration(configuration);
//        return result;
//    }
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 开启后缀匹配模式
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    // 重新注册Servlet的映射
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean =
                new ServletRegistrationBean<>(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("/");
        return servletServletRegistrationBean;
    }
}
