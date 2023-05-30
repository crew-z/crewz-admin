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
public class ClubInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_no")
	private Club club;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;

	private Integer clubUserGrade;

	private Boolean clubUsed;

	private Date clubJoinDate;

	private Date clubApproveDate;

	private Date clubOutDate;

	@Builder
	public ClubInfo(Long idx, Club club, User user, Integer clubUserGrade,
		Boolean clubUsed, Date clubJoinDate, Date clubApproveDate, Date clubOutDate) {
		this.idx = idx;
		this.club = club;
		this.user = user;
		this.clubUserGrade = clubUserGrade;
		this.clubUsed = clubUsed;
		this.clubJoinDate = clubJoinDate;
		this.clubApproveDate = clubApproveDate;
		this.clubOutDate = clubOutDate;
	}
}