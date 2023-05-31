package crewz.admin.crewzadmin.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.entity.ClubInfo;
import crewz.admin.crewzadmin.repository.ClubDashboardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubDashboardService {
	private final ClubDashboardRepository clubDashboardRepository;

	public List<Map<String, Object>> totalUsersByClubNo(Long clubNo) {
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		List<ClubInfo> data = clubDashboardRepository.findAllByClub_ClubNo_AndClubUserGradeInOrderByClubJoinDateAsc(clubNo, nums);

		// 기간별 누적회원수
		Map<YearMonth, Integer> countsByYearMonth = new LinkedHashMap<>(); // 순서 보장을 위해 LinkedHashMap 사용

		int cumulativeCount = 0;
		for (ClubInfo clubInfo : data) {
			YearMonth yearMonth = YearMonth.from(clubInfo.getClubJoinDate().toLocalDate());
			cumulativeCount++;
			countsByYearMonth.put(yearMonth, cumulativeCount);
		}

		List<Map<String, Object>> result = countsByYearMonth.entrySet().stream()
			.map(entry -> {
				Map<String, Object> map = new HashMap<>();
				YearMonth yearMonth = entry.getKey();
				int count = entry.getValue();
				map.put("year", yearMonth.getYear());
				map.put("month", yearMonth.getMonthValue());
				map.put("count", count);
				return map;
			})
			.collect(Collectors.toList());


		return result;
	}
}
