package crewz.admin.crewzadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.dto.RequestUserDeleteDto;
import crewz.admin.crewzadmin.model.dto.ResponseUserDto;
import crewz.admin.crewzadmin.model.entity.User;
import crewz.admin.crewzadmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public ResponseEntity<ResponseUserDto> findUserList(PageRequest pageRequest) {
		Page<User> pageObj = userRepository.findByUserDeleteYn("N", pageRequest);
		List<User> userList = pageObj.stream().collect(Collectors.toList());

		ResponseEntity<ResponseUserDto> entity;
		log.info("totalElements: {}", pageObj.getTotalElements());
		log.info("totalPages: {}", pageObj.getTotalPages());
		log.info("pageNumber: {}", pageObj.getNumber());
		log.info("pageSize: {}", pageObj.getSize());
		PagingUtil pagingUtil = new PagingUtil(pageObj.getTotalElements(), pageObj.getTotalPages(),
			pageObj.getNumber(),
			pageObj.getSize());

		ResponseUserDto responseUserDto = new ResponseUserDto();
		responseUserDto.setPagingUtil(pagingUtil);
		responseUserDto.setUserList(userList);

		entity = ResponseEntity.ok(responseUserDto);
		return entity;

	}

	@Transactional
	public ResponseEntity<String> deleteUser(RequestUserDeleteDto requestUserDeleteDto) {
		ResponseEntity<String> entity;
		try {
			User user = userRepository.findByUserNo(requestUserDeleteDto.getUserNo());
			user.delete("Y");
			entity = new ResponseEntity<>("사용자 삭제에 성공하였습니다.", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("사용자 삭제에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
