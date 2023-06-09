package crewz.admin.crewzadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.ResponseClubDashboardDto;
import crewz.admin.crewzadmin.model.dto.ResponseClubMemberListDto;
import crewz.admin.crewzadmin.repository.ClubDashboardRepository;
import crewz.admin.crewzadmin.service.ClubDashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clubdashboard")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubDashboardController {
	private final ClubDashboardService clubDashboardService;
	private final ClubDashboardRepository clubDashboardRepository;

	@GetMapping("/{clubNo}")
	public ResponseEntity<Map<Integer, List<ResponseClubDashboardDto>>> countUsersByClubNo(@PathVariable Long clubNo) {
		try {
			Map<Integer, List<ResponseClubDashboardDto>> result = clubDashboardService.totalUsersByClubNo(clubNo);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			throw new RuntimeException("월별 누적 회원 수를 가져오는 중 오류가 발생했습니다.", e);
		}
	}
	@PostMapping("/{clubNo}")
	public ResponseEntity<List<ResponseClubMemberListDto>> clubMemberListByClubNo(@PathVariable Long clubNo) {
		try {
			List<ResponseClubMemberListDto> result = clubDashboardService.findClubMemByClubNo(clubNo);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			throw new RuntimeException("회원 리스트를 가져오는 중 오류가 발생했습니다.", e);
		}
	}

	@PatchMapping("/{clubNo}")
	public ResponseEntity<String> clubUserGradeModify(@PathVariable Long clubNo, @RequestParam Long userNo) {
		boolean success = clubDashboardService.modifyClubUserGrade(clubNo, userNo);
		if (success) {
			return ResponseEntity.ok("성공적으로 업데이트했습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("정보를 찾을 수 없습니다.");
		}
	}



}



