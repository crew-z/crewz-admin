package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseLoginDto {
	private String role;
	private String token;
	private String name;

}
