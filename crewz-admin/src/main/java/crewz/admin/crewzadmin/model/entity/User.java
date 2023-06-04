package crewz.admin.crewzadmin.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;
	private String userId;
	private String userName;
	private String userPassword;
	private String userTel;
	private String userNickname;
	private String userEmail;
	private String userDeleteYn;
	private LocalDateTime userDeleteDate;

	@Builder
	public User(Long userNo, String userId, String userName, String userPassword, String userTel, String userNickname,
		String userEmail, String userDeleteYn, LocalDateTime userDeleteDate) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTel = userTel;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
		this.userDeleteYn = userDeleteYn;
		this.userDeleteDate = userDeleteDate;
	}

	public void delete(String userDeleteYn) {
		this.userDeleteYn = userDeleteYn;
		this.userDeleteDate = LocalDateTime.now();
		this.userName = null;
		this.userPassword = null;
		this.userTel = null;
		this.userEmail = null;
	}
}

