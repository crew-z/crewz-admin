package crewz.admin.crewzadmin.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "club_info")
public class ClubInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	private int clubUserGrade;
	private int clubUsed;
	@CreationTimestamp
	LocalDateTime clubJoinDate;
	@CreationTimestamp
	LocalDateTime clubApproveDate;
	LocalDateTime clubOutDate;

	// joinData
	@ManyToOne
	@JoinColumn(name = "club_no")
	private Club club;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@Builder
	public ClubInfo(Long idx, int clubUserGrade, int clubUsed, LocalDateTime clubJoinDate,
		LocalDateTime clubApproveDate,
		LocalDateTime clubOutDate,
		Club club, User user) {
		this.idx = idx;
		this.clubUserGrade = clubUserGrade;
		this.clubUsed = clubUsed;
		this.clubJoinDate = clubJoinDate;
		this.clubApproveDate = clubApproveDate;
		this.clubOutDate = clubOutDate;
		this.club = club;
		this.user = user;
	}
}
