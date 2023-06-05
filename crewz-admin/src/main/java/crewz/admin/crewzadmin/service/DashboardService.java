package crewz.admin.crewzadmin.service;

import java.util.List;
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
		List<Object[]> countByQuarterOfClubCloseDate = clubRepository.countClubByQuarterOfClubCloseDate();
		List<Object[]> clubCountByCategory = boardCategoryRepository.countClubByCategory();

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
}
