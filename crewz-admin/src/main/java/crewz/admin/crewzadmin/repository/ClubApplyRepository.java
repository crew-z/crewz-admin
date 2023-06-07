package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.ClubApply;

public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {
	Page<ClubApply> findByClubApproveYnIsNull(PageRequest pageRequest);

	Page<ClubApply> findByClubApproveYn(String clubApproveYn, PageRequest pageRequest);

}
