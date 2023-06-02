package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	int countBy();
}
