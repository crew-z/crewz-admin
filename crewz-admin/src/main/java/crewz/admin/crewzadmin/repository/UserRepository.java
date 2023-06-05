package crewz.admin.crewzadmin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	int countBy();

	User findByUserNo(Long userNo);

	Page<User> findByUserDeleteYn(String keyword, PageRequest pageRequest);
}
