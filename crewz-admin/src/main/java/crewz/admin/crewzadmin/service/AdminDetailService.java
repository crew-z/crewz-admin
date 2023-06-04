package crewz.admin.crewzadmin.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminDetailService implements UserDetailsService {

	private final AdminRepository adminRepository;
	private final BCryptPasswordEncoder encodePwd;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info("loadUserByUsername : {}", userId);
		AdminUser admin = adminRepository.findByAdminId(userId);
		if (admin == null) {
			throw new UsernameNotFoundException(userId + " 사용자 없음");
		}

		UserDetails user;
		user = User.builder()
			.username(userId)
			.password(admin.getAdminPassword())
			.authorities(AuthorityUtils.createAuthorityList(admin.getAdminRoles()))
			.build();
		log.info("LoginUser -> {}", user);
		return user;

	}
}
