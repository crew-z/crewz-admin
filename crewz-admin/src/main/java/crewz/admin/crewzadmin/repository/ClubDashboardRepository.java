package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import crewz.admin.crewzadmin.model.entity.ClubInfo;

@Repository
public interface ClubDashboardRepository extends JpaRepository<ClubInfo, Long> {

	List<ClubInfo> findAllByClub_ClubNo_AndClubUserGradeInOrderByClubJoinDateAsc(Long clubNo, List<Integer> nums);

	List<ClubInfo> findAllByClub_ClubNo_AndClubUserGradeInOrderByClubUserGradeDesc(Long clubNo, List<Integer> nums);

	@Modifying
	@Query("UPDATE ClubInfo ci SET ci.clubUserGrade = CASE WHEN ci.user.userNo = :userNo THEN 2 ELSE 1 END WHERE ci.club.clubNo = :clubNo")
	int updateClubUserGrade(@Param("clubNo")Long clubNo, @Param("userNo")Long userNo);
}


