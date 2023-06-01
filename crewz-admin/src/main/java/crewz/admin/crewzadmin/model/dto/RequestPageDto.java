package crewz.admin.crewzadmin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPageDto {
	/* REQUEST Arg를 받는 DTO */
	private Integer page = 1;

	private Integer pageSize = 10;

	private String keyword;

	public Integer getPage() {
		if (page - 1 < 0) {
			page = 0;
		}
		return page - 1;
	}
}
