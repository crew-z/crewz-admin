package crewz.admin.crewzadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
	List<AdminUser> findAllByAdminDeleteYn(String deleteYn);
}
