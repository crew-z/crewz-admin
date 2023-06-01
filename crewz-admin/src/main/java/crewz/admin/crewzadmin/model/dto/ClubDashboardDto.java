package crewz.admin.crewzadmin.model.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import crewz.admin.crewzadmin.model.entity.ClubInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubDashboardDto {
	private Long clubNo;
	private LocalDateTime clubJoinDate;
	private int count;

	// public ClubInfo toEntity() {
	// 	return ClubInfo.builder()
	// 		.clubJoinDate(this.getClubJoinDate().getYear())
	// }
}
