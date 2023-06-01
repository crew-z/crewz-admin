package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.CategoryInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCategoryInfoDto {
	private String categoryName;

	public CategoryInfo toEntity() {
		return CategoryInfo.builder()
			.categoryName(this.categoryName)
			.categoryDeleteFlag("N")
			.build();
	}
}
