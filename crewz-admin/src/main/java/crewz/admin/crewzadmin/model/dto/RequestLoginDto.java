package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RequestLoginDto {
	private String adminId;
	private String adminPassword;

	public AdminUser toEntity() {
		return AdminUser.builder()
			.adminId(this.adminId)
			.adminPassword(this.adminPassword)
			.build();
	}
}
