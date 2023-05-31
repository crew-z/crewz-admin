package crewz.admin.crewzadmin.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import crewz.admin.crewzadmin.model.dto.ClubDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
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

	@Builder
	public User(Long userNo, String userId, String userName, String userPassword, String userTel, String userNickname,
		String userEmail, String userDeleteYn) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTel = userTel;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
		this.userDeleteYn = userDeleteYn;
	}
}
