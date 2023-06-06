package crewz.admin.crewzadmin.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "club_apply")
public class ClubApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_apply_no")
	private Long clubApplyNo;
	private String clubName;
	private String clubPurpose;
	private String clubActivities;
	private String clubApproveYn;
	private String clubRefuseReason;
	@CreationTimestamp
	Date regdate;
	@CreationTimestamp
	Date updatedate;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@Builder
	public ClubApply(Long clubApplyNo, String clubName, String clubPurpose, String clubActivities, String clubApproveYn,
		String clubRefuseReason, Date regdate, Date updatedate, User user) {
		this.clubApplyNo = clubApplyNo;
		this.clubName = clubName;
		this.clubPurpose = clubPurpose;
		this.clubActivities = clubActivities;
		this.clubApproveYn = clubApproveYn;
		this.clubRefuseReason = clubRefuseReason;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.user = user;
	}

	public void edit(String clubRefuseReason, String clubApproveYn) {
		this.clubRefuseReason = clubRefuseReason;
		this.clubApproveYn = clubApproveYn;
	}
}
