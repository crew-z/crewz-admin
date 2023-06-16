package crewz.admin.crewzadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.dto.RequestAdminDeleteDto;
import crewz.admin.crewzadmin.model.dto.ResponseAdminDto;
import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminInfoService {
	private final AdminRepository adminRepository;

	public ResponseEntity<ResponseAdminDto> findAdminList(PageRequest pageRequest) {
		Page<AdminUser> pageObj = adminRepository.findByAdminDeleteYn("N", pageRequest);
		List<AdminUser> adminUserList = pageObj.stream().collect(Collectors.toList());

		ResponseEntity<ResponseAdminDto> entity;
		log.debug("totalElements: {}", pageObj.getTotalElements());
		log.debug("totalPages: {}", pageObj.getTotalPages());
		log.debug("pageNumber: {}", pageObj.getNumber());
		log.debug("pageSize: {}", pageObj.getSize());
		PagingUtil pagingUtil = new PagingUtil(pageObj.getTotalElements(), pageObj.getTotalPages(),
			pageObj.getNumber(),
			pageObj.getSize());

		ResponseAdminDto responseAdminDto = new ResponseAdminDto();
		responseAdminDto.setPagingUtil(pagingUtil);
		responseAdminDto.setAdminUserList(adminUserList);

		entity = ResponseEntity.ok(responseAdminDto);
		return entity;

	}

	@Transactional
	public ResponseEntity<String> addAdmin(AdminUser admin) {
		log.debug(admin.toString());
		ResponseEntity<String> entity;
		try {
			adminRepository.save(admin);
			entity = new ResponseEntity<>("관리자 등록에 성공하였습니다.", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("관리자 등록에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@Transactional
	public ResponseEntity<String> deleteAdmin(RequestAdminDeleteDto requestAdminDeleteDto) {
		ResponseEntity<String> entity;
		try {
			AdminUser admin = adminRepository.findByAdminNo(requestAdminDeleteDto.getAdminNo());
			admin.update("Y");

			entity = new ResponseEntity<>("관리자 삭제에 성공하였습니다.", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("관리자 삭제에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
