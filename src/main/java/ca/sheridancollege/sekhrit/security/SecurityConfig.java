package ca.sheridancollege.sekhrit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

		return http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(mvc.pattern("/")).hasAnyRole("USER","ADMIN")
				.requestMatchers(mvc.pattern("/insert/**")).hasAnyRole("USER","ADMIN")
				.requestMatchers(mvc.pattern("/update/**")).hasAnyRole("USER","ADMIN")
				.requestMatchers(mvc.pattern("/delete/**")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/error/**")).permitAll()
				.requestMatchers(mvc.pattern("/images/**")).permitAll()
				.requestMatchers(mvc.pattern("/permission-denied")).permitAll()
				.requestMatchers(mvc.pattern("/register")).permitAll()
				
				.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(mvc.pattern("/api/v1/**")).permitAll()
				
				.requestMatchers(mvc.pattern("/**")).denyAll()
			)
			.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())

			.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
			.exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
			.formLogin(form -> form.loginPage("/login").permitAll())
			
			.logout(logout -> logout.permitAll())
			.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
