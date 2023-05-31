package crewz.admin.crewzadmin.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubApplyDto {
	private Long clubApplyNo;
	private Long userNo;
	private String clubName;
	private String clubPurpose;
	private String clubActivities;
	private String clubApproveYn;
	private String clubRefuseReason;
	private Date regdate;
	private Date updatedate;

	//JoinData
	private Long clubNo;
	private Long boardNo;
	private String userId;
	private String userName;
	private String userEmail;

}
