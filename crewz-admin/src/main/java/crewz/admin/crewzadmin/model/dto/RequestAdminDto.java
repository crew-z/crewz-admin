package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestAdminDto {
	private String adminId;
	private String adminName;
	private String adminPassword;
	private String adminTel;
	private String adminEmail;
	private String adminRoles;

	public AdminUser toEntity() {
		return AdminUser.builder()
			.adminId(adminId)
			.adminPassword(adminPassword)
			.adminName(adminName)
			.adminEmail(adminEmail)
			.adminRoles("ROLE_ADMIN")
			.adminTel(adminTel)
			.build();
	}
}
