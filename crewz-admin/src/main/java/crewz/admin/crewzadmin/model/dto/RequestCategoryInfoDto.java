package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestCategoryInfoDto {
	private String categoryName;

	public CategoryInfo toEntity() {
		return CategoryInfo.builder()
			.categoryName(this.categoryName)
			.categoryDeleteFlag("N")
			.build();
	}
}
