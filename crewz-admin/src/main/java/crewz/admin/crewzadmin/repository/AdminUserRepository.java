package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

}
