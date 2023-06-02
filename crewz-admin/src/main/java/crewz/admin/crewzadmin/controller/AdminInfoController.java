package crewz.admin.crewzadmin.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.RequestAdminDto;
import crewz.admin.crewzadmin.model.dto.RequestPageDto;
import crewz.admin.crewzadmin.model.dto.ResponseAdminDto;
import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.service.AdminInfoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminInfoController {
	private final AdminInfoService adminInfoService;

	@GetMapping
	public ResponseEntity<ResponseAdminDto> adminList(@ModelAttribute RequestPageDto requestPageDto) {
		PageRequest pageRequest = PageRequest.of(requestPageDto.getPage(), requestPageDto.getPageSize(),
			Sort.by("adminNo"));
		return adminInfoService.findAdminList(pageRequest);
	}

	@PostMapping
	public ResponseEntity<String> adminAdd(@RequestBody RequestAdminDto requestAdminDto) {
		log.info("requestAdminDto -> {}",requestAdminDto);
		AdminUser adminUser = requestAdminDto.toEntity();
		return adminInfoService.addAdmin(adminUser);
	}
}
