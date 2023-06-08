package crewz.admin.crewzadmin.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.jwt.TokenProvider;
import crewz.admin.crewzadmin.model.dto.ResponseLoginDto;
import crewz.admin.crewzadmin.model.dto.ResponseUserDto;
import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.repository.AdminRepository;
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
	public ResponseLoginDto login(@RequestBody Map<String, String> user) {
		ResponseLoginDto responseLoginDto = new ResponseLoginDto();
		log.info("user Id => {}", user.get("userId"));
		AdminUser member = userRepository.findByAdminId(user.get("userId"))
			.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Id 입니다."));
		String token = tokenProvider.createToken(member.getUsername(), member.getAdminRoles());

		responseLoginDto.setRole(member.getAdminRoles());
		responseLoginDto.setToken(token);
		responseLoginDto.setName(member.getAdminName());

		return responseLoginDto;
	}

}
