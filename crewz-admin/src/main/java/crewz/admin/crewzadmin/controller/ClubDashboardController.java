package crewz.admin.crewzadmin.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

	@GetMapping("/{clubNo}")
	public ResponseEntity<List<Map<String, Object>>> countUsersByClubNo(@PathVariable Long clubNo) {
		try {
			List<Map<String, Object>> result = clubDashboardService.totalUsersByClubNo(clubNo);
			log.info("stream Date : {}", result);
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new RuntimeException("월별 누적 회원 수를 가져오는 중 오류가 발생했습니다", e);
		}
	}

}

// start of 기간별 신규회원수 //
// Map<YearMonth, Long> countsByYearMonth = all.stream()
// 	.collect(Collectors.groupingBy(
// 		date -> YearMonth.from(date.getClubJoinDate().atStartOfDay().toLocalDate()),
// 		Collectors.counting()
// 	));
//
// List<Map<String, Object>> result = countsByYearMonth.entrySet().stream()
// 	.sorted(Map.Entry.comparingByKey())
// 	.map(entry -> {
// 		Map<String, Object> map = new HashMap<>();
// 		YearMonth yearMonth = entry.getKey();
// 		long count = entry.getValue();
// 		map.put("Year", yearMonth.getYear());
// 		map.put("Month", yearMonth.getMonthValue());
// 		map.put("Count", count);
// 		return map;
// 	})
// 	.collect(Collectors.toList());
// end of 기간별 신규회원수 //



