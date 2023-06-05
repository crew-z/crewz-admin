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
import org.springframework.transaction.annotation.Transactional;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.dto.RequestClubApplyUpdateDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubApplyDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubDto;
import crewz.admin.crewzadmin.model.entity.Club;
import crewz.admin.crewzadmin.model.entity.ClubApply;
import crewz.admin.crewzadmin.model.entity.ClubInfo;
import crewz.admin.crewzadmin.model.entity.User;
import crewz.admin.crewzadmin.repository.ClubApplyRepository;
import crewz.admin.crewzadmin.repository.ClubInfoRepository;
import crewz.admin.crewzadmin.repository.ClubRepository;
import crewz.admin.crewzadmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubService {

	private final UserRepository userRepository;
	private final ClubRepository clubRepository;
	private final ClubApplyRepository clubApplyRepository;
	private final ClubInfoRepository clubInfoRepository;

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

	public ResponseEntity<ResponseClubApplyDto> findClubApplyList(String keyword, PageRequest pageRequest) {
		Page<ClubApply> pageObj;
		if (keyword.equals("wait")) {
			pageObj = clubApplyRepository.findByClubApproveYnIsNull(pageRequest);
		} else if (keyword.equals("approve")) {
			pageObj = clubApplyRepository.findByClubApproveYn("Y", pageRequest);
		} else if (keyword.equals("refuse")) {
			pageObj = clubApplyRepository.findByClubApproveYn("N", pageRequest);
		} else {
			pageObj = clubApplyRepository.findAll(pageRequest);
		}

		List<ClubApply> clubApplyList = pageObj.stream().collect(Collectors.toList());
		PagingUtil pagingUtil = new PagingUtil(pageObj.getTotalElements(), pageObj.getTotalPages(),
			pageObj.getNumber(),
			pageObj.getSize());
		ResponseClubApplyDto responseClubApplyDto = new ResponseClubApplyDto();
		responseClubApplyDto.setPagingUtil(pagingUtil);
		responseClubApplyDto.setClubApplyList(clubApplyList);
		ResponseEntity<ResponseClubApplyDto> entity;

		entity = ResponseEntity.ok(responseClubApplyDto);

		return entity;
	}

	@Transactional
	public void updateClubApply(RequestClubApplyUpdateDto requestClubApplyUpdateDto) {
		ClubApply clubApply = clubApplyRepository.findById(requestClubApplyUpdateDto.getClubApplyNo())
			.orElseThrow(() -> new RuntimeException("매칭되는 신청글이 없습니다"));

		User user = userRepository.findById(requestClubApplyUpdateDto.getUserNo())
			.orElseThrow(() -> new RuntimeException("매칭되는 유저가 없습니다"));

		if (requestClubApplyUpdateDto.getClubRefuseReason() == null) {
			requestClubApplyUpdateDto.setClubRefuseReason("");
		}
		clubApply.edit(requestClubApplyUpdateDto.getClubRefuseReason(), requestClubApplyUpdateDto.getClubApproveYn());

		//동아리 승인 시 최초 동아리 장 생성
		if (requestClubApplyUpdateDto.getClubApproveYn().equals("Y")) {
			Club club = Club.builder().clubApply(clubApply).clubCloseYn("N").build();
			clubRepository.save(club);

			ClubInfo clubInfo = ClubInfo.builder().clubUserGrade(2).user(user).club(club).build();
			clubInfoRepository.save(clubInfo);
		}

	}

}
