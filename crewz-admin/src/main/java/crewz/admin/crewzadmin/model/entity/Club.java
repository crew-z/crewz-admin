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
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clubNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_apply_no")
	private ClubApply clubApply;

	private Date clubCreateDate;

	@Builder
	public Club(Long clubNo, Date clubCreateDate, ClubApply clubApply) {
		this.clubNo = clubNo;
		this.clubCreateDate = clubCreateDate;
		this.clubApply = clubApply;
	}
}