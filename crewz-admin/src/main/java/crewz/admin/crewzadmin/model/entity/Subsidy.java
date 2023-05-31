package crewz.admin.crewzadmin.model.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subsidy",
	indexes = {
		@Index(name = "FK_subsidy_club", columnList = "club_no"),
		@Index(name = "FK_subsidy_admin_user", columnList = "admin_no")
	})
public class Subsidy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_no")
	private Club club;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_no")
	private AdminUser adminUser;

	private int price;
	private Timestamp approveDate;

	@Builder
	public Subsidy(Long idx, Club club, AdminUser adminUser, int price, Timestamp approveDate) {
		this.idx = idx;
		this.club = club;
		this.adminUser = adminUser;
		this.price = price;
		this.approveDate = approveDate;
	}
}
