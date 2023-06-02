package crewz.admin.crewzadmin.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crewz.admin.crewzadmin.model.entity.ClubInfo;
@Repository
public interface ClubDashboardRepository extends JpaRepository<ClubInfo, Long> {

	List<ClubInfo> findAllByClub_ClubNo_AndClubUserGradeInOrderByClubJoinDateAsc(Long clubNo, List<Integer> nums);
	Long countByClub_ClubNoAndClubUserGradeIn(Long clubNo, List<Integer> nums);
}
