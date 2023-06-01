// package crewz.admin.crewzadmin.model.entity;
//
// import java.io.Serializable;
// import java.sql.Date;
//
// import javax.persistence.EmbeddedId;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.Id;
// import javax.persistence.IdClass;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToOne;
//
// import lombok.AccessLevel;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Entity
// @Getter
// @NoArgsConstructor(access = AccessLevel.PUBLIC)
// public class BoardPeriod implements Serializable {
// 	@EmbeddedId
// 	private BoardPeriodId id;
//
// 	@OneToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "board_no")
// 	private Board board;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "user_no")
// 	private User user;
//
// 	private Date startDate;
//
// 	private Date endDate;
//
// 	@Builder
// 	public BoardPeriod(Board board, User user, Date startDate, Date endDate) {
// 		this.id = new BoardPeriodId(board.getBoardNo(), user.getUserNo());
// 		this.board = board;
// 		this.user = user;
// 		this.startDate = startDate;
// 		this.endDate = endDate;
// 	}
// }