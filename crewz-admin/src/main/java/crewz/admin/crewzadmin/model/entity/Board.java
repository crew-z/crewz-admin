package crewz.admin.crewzadmin.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_no")
	private Club club;

	private String boardTitle;

	private String boardPreContent;

	private String boardContent;

	private String boardActiveContent;

	private String boardRegularContent;

	private String boardEtcContent;

	private Integer boardViews;

	private String recruitStatus;

	private Date regdate;

	@UpdateTimestamp
	private Date updatedate;

	@Builder
	public Board(Long boardNo, String boardTitle, String boardPreContent,
		String boardContent, String boardActiveContent,
		String boardRegularContent, String boardEtcContent,
		Integer boardViews, String recruitStatus, Date regdate) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardPreContent = boardPreContent;
		this.boardContent = boardContent;
		this.boardActiveContent = boardActiveContent;
		this.boardRegularContent = boardRegularContent;
		this.boardEtcContent = boardEtcContent;
		this.boardViews = boardViews;
		this.recruitStatus = recruitStatus;
		this.regdate = regdate;
	}
}
