package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crewz.admin.crewzadmin.model.entity.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
	@Query("SELECT c.categoryName, COUNT(b.boardNo) AS club_count\n"
		+ "FROM BoardCategory bc "
		+ "INNER JOIN bc.categoryInfo c "
		+ "INNER JOIN bc.board b "
		+ "INNER JOIN b.club cl "
		+ "GROUP BY c.categoryName")
	List<Object[]> countByCategory();
}
