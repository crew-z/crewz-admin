package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

}
