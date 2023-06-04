package crewz.admin.crewzadmin.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class ResponseDashboardDto {
	private int totalUserCount;
	private int totalClubCount;
	private int totalOperatingClubs;
	private List<Object[]> clubCountByCategory;
	private List<Object[]> totalPriceByQuarter;
	private List<Object[]> countByQuarterOfClubCreateDate;
	private List<Object[]> countByQuarterOfClubCloseDate;

	public ResponseDashboardDto(int totalUserCount, int totalClubCount, int totalOperatingClubs, List<Object[]> clubCountByCategory, List<Object[]> totalPriceByQuarter, List<Object[]> countByQuarterOfClubCreateDate, List<Object[]> countByQuarterOfClubCloseDate) {
		this.totalUserCount = totalUserCount;
		this.totalClubCount = totalClubCount;
		this.totalOperatingClubs = totalOperatingClubs;
		this.clubCountByCategory = clubCountByCategory;
		this.totalPriceByQuarter = totalPriceByQuarter;
		this.countByQuarterOfClubCreateDate = countByQuarterOfClubCreateDate;
		this.countByQuarterOfClubCloseDate = countByQuarterOfClubCloseDate;
	}
}
