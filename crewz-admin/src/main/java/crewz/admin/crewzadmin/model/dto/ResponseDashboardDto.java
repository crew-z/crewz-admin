package crewz.admin.crewzadmin.model.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class ResponseDashboardDto {
	private Long totalUserCount;
	private Long totalClubCount;
	private Long totalOperatingClubs;
	private List<Object[]> clubCountByCategory;
	// private Long totalSubsidyPrice;

	public ResponseDashboardDto(long totalUserCount, long totalClubCount, long totalOperatingClubs, List<Object[]> clubCountByCategory) {
		this.totalUserCount = totalUserCount;
		this.totalClubCount = totalClubCount;
		this.totalOperatingClubs = totalOperatingClubs;
		this.clubCountByCategory = clubCountByCategory;
	}
}
