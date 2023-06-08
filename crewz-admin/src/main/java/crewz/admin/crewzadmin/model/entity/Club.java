package crewz.admin.crewzadmin.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "club")
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_no")
	private Long clubNo;
	@CreationTimestamp
	LocalDateTime clubCreateDate;
	private String clubCloseYn;

	LocalDateTime clubCloseDate;

	@OneToOne
	@JoinColumn(name = "club_apply_no")
	private ClubApply clubApply;

	@Builder
	public Club(Long clubNo, LocalDateTime clubCreateDate, String clubCloseYn, LocalDateTime clubCloseDate, ClubApply clubApply) {
		this.clubNo = clubNo;
		this.clubCreateDate = clubCreateDate;
		this.clubCloseYn = clubCloseYn;
		this.clubCloseDate = clubCloseDate;
		this.clubApply = clubApply;
	}

	public void closeClub(String clubCloseYn) {
		this.clubCloseYn = clubCloseYn;
	}
}
