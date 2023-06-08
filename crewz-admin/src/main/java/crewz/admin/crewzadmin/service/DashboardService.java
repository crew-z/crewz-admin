package crewz.admin.crewzadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.dto.ResponseDashboardDto;
import crewz.admin.crewzadmin.repository.BoardCategoryRepository;
import crewz.admin.crewzadmin.repository.ClubRepository;
import crewz.admin.crewzadmin.repository.SubsidyRepository;
import crewz.admin.crewzadmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
	private final UserRepository userRepository;
	private final ClubRepository clubRepository;
	private final BoardCategoryRepository boardCategoryRepository;
	private final SubsidyRepository subsidyRepository;

	public ResponseDashboardDto findDashboard() {
		int totalUserCount = userRepository.countBy();
		int totalClubCount = clubRepository.countBy();
		int totalOperatingClubs = clubRepository.countByClubCloseYn("N");
		List<Object[]> countByQuarterOfClubCreateDate = clubRepository.countClubByQuarterOfClubCreateDate();
		List<Object[]> clubCountByCategory = boardCategoryRepository.countClubByCategory();

		// raw 데이터 빈값 채우는걸로 변경
		List<Object[]> rawCloseCountByQuarter = clubRepository.countClubByQuarterOfClubCloseDate();
		List<Object[]> countByQuarterOfClubCloseDate = fillMissingQuartersWithZero(countByQuarterOfClubCreateDate, rawCloseCountByQuarter);

		// Top 5
		List<Object[]> top4Categories = clubCountByCategory.stream().limit(4).collect(Collectors.toList());

		// 기타 등등
		long otherCategoryCount = clubCountByCategory.stream().skip(4).mapToLong(category -> (long)category[1]).sum();
		if (otherCategoryCount > 0) {
			top4Categories.add(new Object[] {"기타등등", otherCategoryCount});
		}

		clubCountByCategory = top4Categories;

		List<Object[]> totalPriceByQuarter = subsidyRepository.selectTotalPriceByQuarter();

		return new ResponseDashboardDto(totalUserCount, totalClubCount, totalOperatingClubs, clubCountByCategory, totalPriceByQuarter, countByQuarterOfClubCreateDate, countByQuarterOfClubCloseDate);
	}


	private List<Object[]> fillMissingQuartersWithZero(List<Object[]> createCount, List<Object[]> closeCount) {
		// Convert the closeCount List to a Map
		Map<String, Integer> closeCountMap = closeCount.stream()
			.collect(Collectors.toMap(o -> (String)o[0], o -> ((Number)o[1]).intValue(), (a,b) -> b));

		// Create new List<Object[]> for filledCounts
		List<Object[]> filledCounts = new ArrayList<>();

		// Check for missing quarters in closeCount and add them with a count of 0
		for (Object[] count : createCount) {
			String quarter = (String)count[0];

			int closeCountValue = closeCountMap.getOrDefault(quarter, 0);
			filledCounts.add(new Object[]{quarter, closeCountValue});
		}
		return filledCounts;
	}
}


