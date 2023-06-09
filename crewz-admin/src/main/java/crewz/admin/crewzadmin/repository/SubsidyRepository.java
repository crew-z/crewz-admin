package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crewz.admin.crewzadmin.model.entity.Club;
import crewz.admin.crewzadmin.model.entity.ClubApply;
import crewz.admin.crewzadmin.model.entity.ClubInfo;
import crewz.admin.crewzadmin.model.entity.Subsidy;

public interface SubsidyRepository extends JpaRepository<Subsidy, Long> {
	@Query(value = "SELECT "
		+ "    CASE "
		+ "        WHEN MONTH(approve_date) BETWEEN 1 AND 3 THEN CONCAT(YEAR(approve_date), 'Q1') "
		+ "        WHEN MONTH(approve_date) BETWEEN 4 AND 6 THEN CONCAT(YEAR(approve_date), 'Q2') "
		+ "        WHEN MONTH(approve_date) BETWEEN 7 AND 9 THEN CONCAT(YEAR(approve_date), 'Q3') "
		+ "        WHEN MONTH(approve_date) BETWEEN 10 AND 12 THEN CONCAT(YEAR(approve_date), 'Q4') "
		+ "    END AS Quarter, "
		+ "    SUM(price) AS Total_Price "
		+ "FROM "
		+ "    subsidy "
		+ "GROUP BY "
		+ "    Quarter "
		+ "ORDER BY "
		+ "    Quarter ASC", nativeQuery = true)
	List<Object[]> selectTotalPriceByQuarter();

	@Query("SELECT s.price AS price,s.approveDate AS approveDate FROM Subsidy s"
		+ " WHERE s.club.clubNo = :clubNo"
		+ " AND YEAR(s.approveDate) = :year"
		+ " ORDER BY s.approveDate")
	List<ResponseClubSubsidyInterface> findByMonthSubsidy(@Param("clubNo") Long clubNo, @Param("year") int year);


}


