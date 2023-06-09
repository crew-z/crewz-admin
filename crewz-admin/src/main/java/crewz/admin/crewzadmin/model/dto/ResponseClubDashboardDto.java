package crewz.admin.crewzadmin.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseClubDashboardDto {
	private int year;
	private int month;
	private int count;

	public ResponseClubDashboardDto(int year, int month, int count) {
		this.year = year;
		this.month = month;
		this.count = count;
	}

}
