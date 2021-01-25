package comptoirs;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
// import org.springframework.data.web.config.EnableSpringDataWebSupport;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// cf.
// https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-enablewebmvc-annotation.html
// @EnableWebMvc
// cf.
// https://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/web/config/EnableSpringDataWebSupport.html
// @EnableSpringDataWebSupport
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// @Bean
	// TODO : Marche pas, dommage !
	// Configure le mapper pour qu'il sérialise les champs LAZY avec leurs clés
	// seulement
	public Hibernate5Module hibernate5Module() {
		Hibernate5Module hbModule = new Hibernate5Module();
		hbModule.disable(Feature.FORCE_LAZY_LOADING);
		hbModule.disable(Feature.REQUIRE_EXPLICIT_LAZY_LOADING_MARKER);
		hbModule.enable(Feature.USE_TRANSIENT_ANNOTATION);
		hbModule.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
		return hbModule;
	}

	@Bean
	// Configure le mapper pour qu'il respecte les annotations JAXB, en particulier
	// XMLTransient
	public JaxbAnnotationModule JaxbAnnotationModule() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		return module;
	}
}
