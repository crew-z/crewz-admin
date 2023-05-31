package crewz.admin.crewzadmin.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminNo;

	private String adminId;

	private String adminName;

	private String adminPassword;

	private String adminTel;

	private String adminEmail;

	private String adminRoles;

	@Builder
	public AdminUser(Long adminNo, String adminId, String adminName, String adminPassword,
		String adminTel, String adminEmail, String adminRoles) {
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminTel = adminTel;
		this.adminEmail = adminEmail;
		this.adminRoles = adminRoles;
	}
}
