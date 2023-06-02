package crewz.admin.crewzadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crewz.admin.crewzadmin.model.dto.ResponseDashboardDto;
import crewz.admin.crewzadmin.repository.BoardCategoryRepository;
import crewz.admin.crewzadmin.repository.ClubRepository;
// import crewz.admin.crewzadmin.repository.SubsidyRepository;
import crewz.admin.crewzadmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
	private final UserRepository userRepository;
	private final ClubRepository clubRepository;
	private final BoardCategoryRepository boardCategoryRepository;
	// private final SubsidyRepository subsidyRepository;


	public ResponseDashboardDto getDashboard() {
		long totalUserCount = userRepository.countBy();
		long totalClubCount = clubRepository.count();
		long totalOperatingClubs = clubRepository.countByClubCloseYn("N");
		List<Object[]> clubCountByCategory = boardCategoryRepository.countByCategory();
		// long totalSubsidyPrice = subsidyRepository.sumPrice();

		return new ResponseDashboardDto(totalUserCount, totalClubCount, totalOperatingClubs, clubCountByCategory);
	}
}
