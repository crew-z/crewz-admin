package crewz.admin.crewzadmin.model.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class ResponseDashboardDto {
	private int totalUserCount;
	private int totalClubCount;
	private int totalOperatingClubs;
	private List<Object[]> clubCountByCategory;
	// private Long totalSubsidyPrice;
	private List<Object[]> totalPriceByQuarter;

	public ResponseDashboardDto(int totalUserCount, int totalClubCount, int totalOperatingClubs, List<Object[]> clubCountByCategory, List<Object[]> totalPriceByQuarter) {
		this.totalUserCount = totalUserCount;
		this.totalClubCount = totalClubCount;
		this.totalOperatingClubs = totalOperatingClubs;
		this.clubCountByCategory = clubCountByCategory;
		this.totalPriceByQuarter = totalPriceByQuarter;
	}
}
