package crewz.admin.crewzadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.ClubDashboardDto;
import crewz.admin.crewzadmin.repository.ClubDashboardRepository;
import crewz.admin.crewzadmin.service.ClubDashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clubmenagement/clubdashboard")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubDashboardController {
	private final ClubDashboardService clubDashboardService;
	private final ClubDashboardRepository clubDashboardRepository;

	@GetMapping("/{clubNo}")
	public ResponseEntity<Map<Integer, List<ClubDashboardDto>>> countUsersByClubNo(@PathVariable Long clubNo) {
		try {
			Map<Integer, List<ClubDashboardDto>> result = clubDashboardService.totalUsersByClubNo(clubNo);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			throw new RuntimeException("월별 누적 회원 수를 가져오는 중 오류가 발생했습니다", e);
		}
	}


}



