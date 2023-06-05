package crewz.admin.crewzadmin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.dto.ResponseClubDto;
import crewz.admin.crewzadmin.model.entity.Club;
import crewz.admin.crewzadmin.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubService {

	private final ClubRepository clubRepository;

	public ResponseEntity<ResponseClubDto> findClubList(PageRequest pageRequest) {
		Page<Club> pageObj = clubRepository.findAll(pageRequest);
		List<Club> categoryInfoList = pageObj.stream().collect(Collectors.toList());
		log.info("categoryInfoList: {}", categoryInfoList);
		ResponseEntity<ResponseClubDto> entity;
		log.info("totalElements: {}", pageObj.getTotalElements());
		log.info("totalPages: {}", pageObj.getTotalPages());
		log.info("pageNumber: {}", pageObj.getNumber());
		log.info("pageSize: {}", pageObj.getSize());
		PagingUtil pagingUtil = new PagingUtil(pageObj.getTotalElements(), pageObj.getTotalPages(),
			pageObj.getNumber(),
			pageObj.getSize());
		ResponseClubDto responseClubDto = new ResponseClubDto();
		responseClubDto.setPagingUtil(pagingUtil);
		responseClubDto.setClubList(categoryInfoList);

		entity = ResponseEntity.ok(responseClubDto);
		return entity;
	}
}
