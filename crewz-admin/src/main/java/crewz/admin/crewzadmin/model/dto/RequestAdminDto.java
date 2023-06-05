package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class RequestAdminDto {
	private String adminId;
	private String adminName;
	private String adminPassword;
	private String adminTel;
	private String adminEmail;
	private String adminRoles;
	private String adminDeleteYn;

	public AdminUser toEntity() {
		return AdminUser.builder()
			.adminId(this.adminId)
			.adminPassword(this.adminPassword)
			.adminName(this.adminName)
			.adminEmail(this.adminEmail)
			.adminRoles("ROLE_ADMIN")
			.adminTel(this.adminTel)
			.adminDeleteYn("N")
			.build();
	}
}
