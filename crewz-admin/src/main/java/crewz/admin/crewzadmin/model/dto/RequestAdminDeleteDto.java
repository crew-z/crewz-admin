package crewz.admin.crewzadmin.model.dto;

import javax.swing.*;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RequestAdminDeleteDto {
	private Long adminNo;
	private String adminDeleteYn;
}
