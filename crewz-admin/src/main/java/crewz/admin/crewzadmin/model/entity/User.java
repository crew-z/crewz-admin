package crewz.admin.crewzadmin.model.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;

	private String userId;

	private String userName;

	private String userPassword;

	private String userTel;

	private String userNickname;

	private String userEmail;

	@Builder
	public User(Long userNo, String userId, String userName, String userPassword,
		String userTel, String userNickname, String userEmail) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTel = userTel;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
	}
}
