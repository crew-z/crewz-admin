package crewz.admin.crewzadmin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.ResponseAdminListDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubListDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubSubsidyDto;
import crewz.admin.crewzadmin.model.dto.RequestClubSubsidyDto;
import crewz.admin.crewzadmin.model.entity.Subsidy;
import crewz.admin.crewzadmin.repository.ResponseClubSubsidyInterface;
import crewz.admin.crewzadmin.service.ClubSubsidyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clubsubsidy")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubSubsidyController {

	private final ClubSubsidyService clubSubsidyService;

	@GetMapping
	public ResponseEntity<List<ResponseClubSubsidyInterface>> countUsersByClubNo(
		@ModelAttribute RequestClubSubsidyDto requestClubSubsidyDto) {
		return ResponseEntity.ok(clubSubsidyService.findClubSubsidyList(requestClubSubsidyDto));
	}

	@GetMapping("/clublist")
	public ResponseEntity<List<ResponseClubListDto>> clubNameList(){
		try {
			List<ResponseClubListDto> result = clubSubsidyService.findClubNoAndClubName();
			log.info("result : {}", result);
			return ResponseEntity.ok(result);
		}catch (Exception e) {
			throw new RuntimeException("데이터를 가져오는데 실패했습니다.");
		}
	}

	@GetMapping("/adminlist")
	public ResponseEntity<List<ResponseAdminListDto>> clubAdminList() {
		try {
			List<ResponseAdminListDto> result = clubSubsidyService.findAdminNameAndAdminNo();
			return ResponseEntity.ok(result);
		}catch (Exception e) {
			throw new RuntimeException("데이터를 가져오는데 실패했습니다.");
		}
	}

	@PostMapping
	public ResponseEntity<String> subsidyAdd(@RequestBody ResponseClubSubsidyDto responseClubSubsidyDto) {
		Subsidy subsidy = responseClubSubsidyDto.toEntity();
		return clubSubsidyService.addSubsidy(subsidy);
	}

}



