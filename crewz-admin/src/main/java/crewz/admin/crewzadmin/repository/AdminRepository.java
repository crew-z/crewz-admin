package crewz.admin.crewzadmin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.AdminUser;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {
	Optional<AdminUser> findByAdminId(String adminId);

	AdminUser findByAdminNo(Long adminNo);

	Page<AdminUser> findByAdminDeleteYn(String keyword, PageRequest pageRequest);
}
