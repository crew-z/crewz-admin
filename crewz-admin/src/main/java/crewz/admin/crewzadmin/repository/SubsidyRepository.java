// package crewz.admin.crewzadmin.repository;
//
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
//
// import crewz.admin.crewzadmin.model.entity.Subsidy;
//
// public interface SubsidyRepository extends JpaRepository<Subsidy, Long> {
// 	@Query("SELECT SUM(s.price) FROM Subsidy s")
// 	Long sumPrice();
// }
