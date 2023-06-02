package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
