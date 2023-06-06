package crewz.admin.crewzadmin.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUserDeleteDto {
	private Long userNo;
	private String userDeleteYn;
}
