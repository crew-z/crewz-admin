package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.AdminUser;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {
	AdminUser findByAdminId(String adminId);
	AdminUser findByAdminNo(Long adminNo);
}
