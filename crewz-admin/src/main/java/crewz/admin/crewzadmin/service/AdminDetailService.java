package crewz.admin.crewzadmin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return adminRepository.findByAdminId(username)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

	}

}
