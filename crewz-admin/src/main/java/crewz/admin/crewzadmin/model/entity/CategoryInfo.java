package crewz.admin.crewzadmin.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CategoryInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryNo;
	private String categoryName;
	private String categoryDeleteFlag;
	@CreationTimestamp
	private LocalDateTime categoryCreateDate;

	@Builder
	public CategoryInfo(Long categoryNo, String categoryName, String categoryDeleteFlag) {
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.categoryDeleteFlag = categoryDeleteFlag;
	}
}