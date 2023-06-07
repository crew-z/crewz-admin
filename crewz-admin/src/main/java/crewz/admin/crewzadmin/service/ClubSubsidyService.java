package crewz.admin.crewzadmin.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.dto.AdminListDto;
import crewz.admin.crewzadmin.model.dto.ClubListDto;
import crewz.admin.crewzadmin.model.dto.RequestClubSubsidyDto;
import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.model.entity.Club;
import crewz.admin.crewzadmin.model.entity.ClubApply;
import crewz.admin.crewzadmin.model.entity.Subsidy;
import crewz.admin.crewzadmin.repository.AdminUserRepository;
import crewz.admin.crewzadmin.repository.ClubApplyRepository;
import crewz.admin.crewzadmin.repository.ClubRepository;
import crewz.admin.crewzadmin.repository.ResponseClubSubsidyInterface;
import crewz.admin.crewzadmin.repository.SubsidyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubSubsidyService {
	private final SubsidyRepository subsidyRepository;
	private final ClubRepository clubRepository;
	private final AdminUserRepository adminUserRepository;

	public List<ResponseClubSubsidyInterface> findClubSubsidyList(RequestClubSubsidyDto requestClubSubsidyDto) {
		return subsidyRepository.findByMonthSubsidy(requestClubSubsidyDto.getClubNo(), requestClubSubsidyDto.getYear());

	}

	public List<ClubListDto> findClubNoAndClubName() {
		List<Club> data = clubRepository.findAll();
		List<ClubListDto> clubNameList = data.stream()
			.map(club -> ClubListDto.builder()
				.clubNo(club.getClubNo())
				.clubName(club.getClubApply().getClubName())
				.build())
			.collect(Collectors.toList());
		log.info("clubNameList : {}", clubNameList);
		return clubNameList;
	}

	public List<AdminListDto> findAdminNameAndAdminNo(){
		List<AdminUser> data = adminUserRepository.findAll();
		List<AdminListDto> AdminList = data.stream()
			.map(adminUser -> AdminListDto.builder()
				.adminNo(adminUser.getAdminNo())
				.adminName(adminUser.getAdminName())
				.build())
			.collect(Collectors.toList());
		log.info("AdminList : {}", AdminList);
		return AdminList;
	}

	@Transactional
	public ResponseEntity<String> addSubsidy(Subsidy subsidy){
		ResponseEntity<String> entity;
		try {
			subsidyRepository.save(subsidy);
			entity = new ResponseEntity<>("지원금을 정상적으로 등록하였습니다", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("지원금을 등록하는데 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
