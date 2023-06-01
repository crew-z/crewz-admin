package crewz.admin.crewzadmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crewz.admin.crewzadmin.model.dto.ResponseDashboardDto;
import crewz.admin.crewzadmin.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
	private final DashboardService dashboardService;

	@GetMapping("/main")
	public ResponseEntity<ResponseDashboardDto> getDashboard() {
		ResponseDashboardDto res = dashboardService.getDashboard();
		return ResponseEntity.ok(res);
	}
}
