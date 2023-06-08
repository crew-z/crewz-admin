package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crewz.admin.crewzadmin.model.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
	int countBy();

	int countByClubCloseYn(String closeYn);

	@Query("SELECT c from Club c "
		+ "left join fetch ClubApply ca "
		+ "ON c.clubApply.clubApplyNo = ca.clubApplyNo "
		+ "where ca.clubApproveYn = :clubApproveYn")
	Page<Club> findByClub(@Param("clubApproveYn") String clubApproveYn, PageRequest pageRequest);

	@Query(value = "SELECT " +
		"    CASE " +
		"        WHEN MONTH(club_create_date) BETWEEN 1 AND 3 THEN CONCAT(YEAR(club_create_date), 'Q1') " +
		"        WHEN MONTH(club_create_date) BETWEEN 4 AND 6 THEN CONCAT(YEAR(club_create_date), 'Q2') " +
		"        WHEN MONTH(club_create_date) BETWEEN 7 AND 9 THEN CONCAT(YEAR(club_create_date), 'Q3') " +
		"        WHEN MONTH(club_create_date) BETWEEN 10 AND 12 THEN CONCAT(YEAR(club_create_date), 'Q4') " +
		"    END AS Quarter, " +
		"    COUNT(*) as count " +
		"FROM " +
		"    club " +
		"GROUP BY " +
		"    Quarter " +
		"ORDER BY " +
		"    Quarter ASC", nativeQuery = true)
	List<Object[]> countClubByQuarterOfClubCreateDate();

	@Query(value = "SELECT " +
		"    CASE " +
		"        WHEN MONTH(club_close_date) BETWEEN 1 AND 3 THEN CONCAT(YEAR(club_close_date), 'Q1') " +
		"        WHEN MONTH(club_close_date) BETWEEN 4 AND 6 THEN CONCAT(YEAR(club_close_date), 'Q2') " +
		"        WHEN MONTH(club_close_date) BETWEEN 7 AND 9 THEN CONCAT(YEAR(club_close_date), 'Q3') " +
		"        WHEN MONTH(club_close_date) BETWEEN 10 AND 12 THEN CONCAT(YEAR(club_close_date), 'Q4') " +
		"    END AS Quarter, " +
		"    COUNT(*) as count " +
		"FROM " +
		"    club " +
		"WHERE " +
		"    club_close_date IS NOT NULL " +
		"GROUP BY " +
		"    Quarter " +
		"ORDER BY " +
		"    Quarter ASC", nativeQuery = true)
	List<Object[]> countClubByQuarterOfClubCloseDate();

	List<Club> findClubByClubCloseYn(String closeyn);
}

