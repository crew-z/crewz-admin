package crewz.admin.crewzadmin.model.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Subsidy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	private int price;
	private LocalDateTime approveDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_no")
	private Club club;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_no")
	private AdminUser adminUser;

	@Builder
	public Subsidy(Long idx, Club club, AdminUser adminUser, int price, LocalDateTime approveDate) {
		this.idx = idx;
		this.club = club;
		this.adminUser = adminUser;
		this.price = price;
		this.approveDate = approveDate;
	}
}
