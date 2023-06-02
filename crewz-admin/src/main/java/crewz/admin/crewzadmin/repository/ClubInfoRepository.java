package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.ClubInfo;

public interface ClubInfoRepository extends JpaRepository<ClubInfo, Long> {
}
