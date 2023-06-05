package crewz.admin.crewzadmin.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.ResponseDashboardDto;
import crewz.admin.crewzadmin.service.ClubService;
import crewz.admin.crewzadmin.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
	private final DashboardService dashboardService;
	private final ClubService clubService;

	@GetMapping("/main")
	public ResponseEntity<ResponseDashboardDto> dashboardList() {
		ResponseDashboardDto res = dashboardService.findDashboard();
		System.out.println(res.getCountByQuarterOfClubCloseDate());
		System.out.println(res.getCountByQuarterOfClubCreateDate());
		return ResponseEntity.ok(res);
	}
}
