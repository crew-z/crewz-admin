package crewz.admin.crewzadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crewz.admin.crewzadmin.model.entity.CategoryInfo;

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Long> {
}
