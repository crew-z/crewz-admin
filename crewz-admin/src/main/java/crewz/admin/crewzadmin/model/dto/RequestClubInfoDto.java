package crewz.admin.crewzadmin.model.dto;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestClubInfoDto {
	private Long idx;
	private int clubUserGrade;
	private boolean clubUsed;
	private LocalDateTime clubJoinDate;
	private LocalDateTime clubApproveDate;
	private LocalDateTime clubOutDate;

	// JoinData
	private Long clubNo;
	private Long userNo;
}
