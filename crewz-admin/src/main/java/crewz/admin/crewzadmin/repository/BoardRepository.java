package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crewz.admin.crewzadmin.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	// @Query("SELECT b from Board b left join fetch BoardCategory c ON b.boardNo = c.board.boardNo where b.boardNo in "
	// 	+ "(SELECT a.board.boardNo from BoardCategory a where a.categoryInfo.categoryNo = :categoryNo)")
	// List<Board> findByCategoryNo(@Param("categoryNo") Long categoryNo);

	@Query("SELECT b from Board b "
		+ "left join fetch BoardCategory c "
		+ "ON b.boardNo = c.board.boardNo "
		+ "where c.categoryInfo.categoryNo = :categoryNo")
	List<Board> findByBoardInfo(@Param("categoryNo") Long categoryNo);
}
