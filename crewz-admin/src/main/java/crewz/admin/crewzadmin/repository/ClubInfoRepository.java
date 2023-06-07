package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crewz.admin.crewzadmin.model.entity.ClubInfo;

public interface ClubInfoRepository extends JpaRepository<ClubInfo, Long> {
	List<ClubInfo> findAllByUser_UserNo(Long userNo);
}
