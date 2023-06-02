package crewz.admin.crewzadmin.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubDashboardDto {
	private int year;
	private int month;
	private int count;

	public ClubDashboardDto(int year, int month, int count) {
		this.year = year;
		this.month = month;
		this.count = count;
	}

}
