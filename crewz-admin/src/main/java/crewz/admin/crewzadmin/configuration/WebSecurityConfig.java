package crewz.admin.crewzadmin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import crewz.admin.crewzadmin.jwt.JwtAuthenticationFilter;
import crewz.admin.crewzadmin.jwt.TokenProvider;
import crewz.admin.crewzadmin.repository.AdminRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration // IoC bean 등록
@EnableWebSecurity    // 필터 체인 관리 시작 어노테이션
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

	private TokenProvider tokenProvider;

	public WebSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests() // HttpServletRequest 객체를 통해 request 를 받겠다는 의미
			.antMatchers("/error/**", "/js/**", "/css/**", "/image/**").permitAll()
			// .antMatchers("/api/admin/**").hasRole("CAPTAIN") // ROLE_CAPTAIN 만 access 가능
			// .antMatchers("/api/user/**").hasRole("CAPTAIN")
			// .antMatchers("/api/**").authenticated()
			.antMatchers("/api/login", "/login").permitAll()
			.anyRequest().permitAll() // 모든 경로에 대한 요청은 인증을 한 후에만 접근 가능
			.and()
			.formLogin().disable();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
