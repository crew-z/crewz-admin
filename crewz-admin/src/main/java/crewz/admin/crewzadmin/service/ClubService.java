package crewz.admin.crewzadmin.service;

import java.util.List;
import java.util.stream.Collectors;

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

	// @GetMapping("/listOne")
	// public ResponseEntity<Club> clubListOne() {
	// 	News news = newsRepository.findById(id)
	// 		.orElseThrow(() -> new RuntimeException("ID에 해당하는 news가 존재하지 않습니다."));
	//
	// 	newsRepository.updateCnt(id);
	//
	// 	model.addAttribute("news", news.toResponseDTO());
	//
	// 	return "newscontent";
	// }
}
