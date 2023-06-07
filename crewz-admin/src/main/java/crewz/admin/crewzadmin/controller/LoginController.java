package crewz.admin.crewzadmin.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.jwt.TokenProvider;
import crewz.admin.crewzadmin.model.dto.RequestLoginDto;
import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.repository.AdminRepository;
import crewz.admin.crewzadmin.service.AdminInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	private final TokenProvider tokenProvider;
	private final AdminRepository userRepository;

	@PostMapping
	public String login(@RequestBody Map<String, String> user) {

		log.info("user Id => {}", user.get("userId"));
		AdminUser member = userRepository.findByAdminId(user.get("userId"))
			.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Id 입니다."));

		return tokenProvider.createToken(member.getUsername(), member.getAdminRoles());
	}

}
