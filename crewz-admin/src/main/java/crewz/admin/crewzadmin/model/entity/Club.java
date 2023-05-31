package crewz.admin.crewzadmin.model.entity;

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
	Date clubCreateDate;
	private String clubCloseYn;

	@OneToOne
	@JoinColumn(name = "club_apply_no")
	private ClubApply clubApply;

	@Builder
	public Club(Long clubNo, Date clubCreateDate, String clubCloseYn, ClubApply clubApply) {
		this.clubNo = clubNo;
		this.clubCreateDate = clubCreateDate;
		this.clubCloseYn = clubCloseYn;
		this.clubApply = clubApply;
	}
}
