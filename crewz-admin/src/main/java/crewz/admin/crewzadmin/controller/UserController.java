package crewz.admin.crewzadmin.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestAdminDeleteDto;
import crewz.admin.crewzadmin.model.dto.RequestPageDto;
import crewz.admin.crewzadmin.model.dto.RequestUserDeleteDto;
import crewz.admin.crewzadmin.model.dto.ResponseAdminDto;
import crewz.admin.crewzadmin.model.dto.ResponseUserDto;
import crewz.admin.crewzadmin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserService userService;

	@GetMapping
	public ResponseEntity<ResponseUserDto> userList(@ModelAttribute RequestPageDto requestPageDto) {
		PageRequest pageRequest = PageRequest.of(requestPageDto.getPage(), requestPageDto.getPageSize(),
			Sort.by("userNo"));
		return userService.findUserList(pageRequest);
	}

	@PatchMapping
	public ResponseEntity<String> userDelete(@RequestBody RequestUserDeleteDto requestUserDeleteDto) {
		log.info("deleteUser -> {}", requestUserDeleteDto);
		return userService.deleteUser(requestUserDeleteDto);
	}

}
