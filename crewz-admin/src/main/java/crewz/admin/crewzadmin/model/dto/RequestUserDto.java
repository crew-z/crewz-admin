package crewz.admin.crewzadmin.model.dto;

import crewz.admin.crewzadmin.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RequestUserDto {
	private String userId;
	private String userName;
	private String userTel;
	private String userNickname;
	private String userEmail;
	private String userDeleteYn;

	public User toEntity() {
		return User.builder()
			.userId(this.userId)
			.userName(this.userName)
			.userTel(this.userTel)
			.userNickname(this.userNickname)
			.userEmail(this.userEmail)
			.userDeleteYn(this.userDeleteYn)
			.build();
	}

}
