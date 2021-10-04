package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

/*
SpringResourceTemplateResolver - Реализация org.thymeleaf.templateresolver.ITemplateResolver, который расширяет
    AbstractConfigurableTemplateResolver и разрешает шаблоны с помощью механизма разрешения ресурсов Spring
SpringTemplateEngine - Реализация ISpringTemplateEngine, предназначенная для приложений с поддержкой Spring,
    которая по умолчанию устанавливает экземпляр SpringStandardDialect в качестве диалекта (вместо экземпляра org.thymeleaf.standard.StandardDialect).
    Он также настраивает SpringMessageResolver как Resolver сообщений и реализует интерфейс MessageSourceAware, чтобы позволить Spring автоматически
    устанавливать MessageSource, используемый в приложении (bean-компонент должен иметь идентификатор messageSource).
templateEngine.setEnableSpringELCompiler() -Устанавливает, должен ли компилятор SpringEL быть включен в выражениях SpringEL или нет.
    Подробнее о SpringEl - https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions
    Реализация интерфейса Spring WebMVC org.springframework.web.servlet.ViewResolver.
ThymeleafViewResolver - Резолверы представлений выполняются после того, как контроллер завершает свое выполнение. Они получают имя view,
    которое будет обработано, и отвечают за создание (и настройку) соответствующего объекта View для него.
* */

@Configuration
@EnableWebMvc
@ComponentScan("web")
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}