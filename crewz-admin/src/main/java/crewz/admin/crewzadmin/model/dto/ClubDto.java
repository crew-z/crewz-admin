package crewz.admin.crewzadmin.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubDto {
	private Long clubNo;
	private Date clubCreateDate;
	private String clubCloseYn;
	private Date clubCloseDate;
	// JoinData
	private Long clubApplyNo;
}
