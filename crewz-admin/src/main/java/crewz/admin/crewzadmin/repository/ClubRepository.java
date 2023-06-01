package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
