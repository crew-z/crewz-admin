package crewz.admin.crewzadmin.model.dto;

import java.time.LocalDateTime;

import crewz.admin.crewzadmin.model.entity.AdminUser;
import crewz.admin.crewzadmin.model.entity.Club;
import crewz.admin.crewzadmin.model.entity.Subsidy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClubSubsidyDto {
	private Club clubNo;
	private AdminUser adminNo;
	private int price;

	public Subsidy toEntity() {
		return Subsidy.builder()
			.club(this.clubNo)
			.adminUser(this.adminNo)
			.price(this.price)
			.build();
	}

}
