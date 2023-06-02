package crewz.admin.crewzadmin.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.dto.ClubDashboardDto;
import crewz.admin.crewzadmin.model.entity.ClubInfo;
import crewz.admin.crewzadmin.repository.ClubDashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubDashboardService {
	private final ClubDashboardRepository clubDashboardRepository;

	public Map<Integer, List<ClubDashboardDto>> totalUsersByClubNo(Long clubNo) {
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		List<ClubInfo> data = clubDashboardRepository.findAllByClub_ClubNo_AndClubUserGradeInOrderByClubJoinDateAsc(clubNo, nums);

		// 기간별 누적회원수
		Map<YearMonth, Integer> countsByYearMonth = new TreeMap<>(); // 순서 보장을 위해 TreeMap 사용

		int cumulativeCount = 0;
		for (ClubInfo clubInfo : data) {
			YearMonth yearMonth = YearMonth.from(clubInfo.getClubJoinDate().toLocalDate());
			cumulativeCount++;
			countsByYearMonth.put(yearMonth, cumulativeCount);
		}

		// year를 기준으로 month와 count로 구성된 객체 생성
		Map<Integer, List<ClubDashboardDto>> result = countsByYearMonth.keySet().stream()
			.collect(Collectors.groupingBy(
				yearMonth -> yearMonth.getYear(),
				Collectors.mapping(
					yearMonth -> new ClubDashboardDto(
						yearMonth.getYear(),
						yearMonth.getMonthValue(),
						countsByYearMonth.get(yearMonth)
					),
					Collectors.toList()
				)
			));
		log.info("countsByYearMonth: {}", countsByYearMonth);
		log.info("result: {}", result);

		return result;
	}
	public Long maxUserByClubNo(Long clubNo){
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		Long max = clubDashboardRepository.countByClub_ClubNoAndClubUserGradeIn(clubNo, nums);
		log.info("max : {}", max);
		return max;
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
