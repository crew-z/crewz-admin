package crewz.admin.crewzadmin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestClubApplyUpdateDto {
	private Long clubApplyNo;
	private Long userNo;
	private String clubRefuseReason;
	private String clubApproveYn;
}
