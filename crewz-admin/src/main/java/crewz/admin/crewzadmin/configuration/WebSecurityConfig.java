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
		// Authentication : 인증
		// Authorization : 인가, 권한부여

		http.csrf().disable(); // csrf: post방식으로 값을 전송할 때, token을 사용해야 하는 보안설정 -> 끔

		http.authorizeRequests() // HttpServletRequest 객체를 통해 request 를 받겠다는 의미
			.antMatchers("/error/**", "/js/**", "/css/**", "/image/**").permitAll()
			// .antMatchers("/api/admin/**").hasRole("CAPTAIN") // ROLE_CAPTAIN 만 access 가능
			// .antMatchers("/api/user/**").hasRole("CAPTAIN")
			// .antMatchers("/api/**").authenticated()
			.antMatchers("/api/login", "/login").permitAll()
			.anyRequest().permitAll() // 모든 경로에 대한 요청은 인증을 한 후에만 접근 가능
			.and()
			.formLogin().disable();
		// .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
		// .addFilter(new JwtAuthorizationFilter(authenticationManage(), adminRepository));
		// .loginPage("/login")// 인가되지 않은 사용자에게 보여줄 페이지
		// .permitAll()
		// .loginProcessingUrl("/api/login") // 로그인 처리 URL, 자동 로그인을 위한 method, id/password를 경로로 던지면 자동으로 로그인 되도록 구현
		// .successHandler((request, response, authentication) -> {
		// 	log.info("authentication name = {}", authentication.getName());
		// 	response.sendRedirect("/");
		// }) // 성공 Handler
		// .failureHandler((request, response, exception) -> {
		// 	log.info("exception.getMessage() = {}", exception.getMessage());
		// 	response.sendRedirect("/api/login");
		// }); // 실패 Handler
		// http.logout()
		// 	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
		// 	.logoutSuccessUrl("/")
		// 	.invalidateHttpSession(true) // 인증정보를 지우고 세션 무효화
		// 	.deleteCookies("JSESSIONID");
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// .maximumSessions(1) // 세션 최대 허용 수
		// .maxSessionsPreventsLogin(false); // true: 중복 로그인 제한, false: 이전 로그인 세션 삭제

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
