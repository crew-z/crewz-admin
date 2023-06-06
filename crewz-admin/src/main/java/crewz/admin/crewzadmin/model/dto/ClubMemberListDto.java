package crewz.admin.crewzadmin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubMemberListDto {
	private Long clubNo;
	private Long userNo;
	private int clubUserGrade;
	private String userName;
	private String userTel;
	private String userEmail;

}
