package crewz.admin.crewzadmin.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ClubApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clubApplyNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;

	private String clubName;

	private String clubPurpose;

	private String clubActivities;

	private String clubApproveYn;

	private String clubRefuseReason;

	private Date regdate;

	private Date updatedate;

	@Builder
	public ClubApply(Long clubApplyNo, User user, String clubName, String clubPurpose,
		String clubActivities, String clubApproveYn, String clubRefuseReason, Date regdate, Date updatedate) {
		this.clubApplyNo = clubApplyNo;
		this.user = user;
		this.clubName = clubName;
		this.clubPurpose = clubPurpose;
		this.clubActivities = clubActivities;
		this.clubApproveYn = clubApproveYn;
		this.clubRefuseReason = clubRefuseReason;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
}