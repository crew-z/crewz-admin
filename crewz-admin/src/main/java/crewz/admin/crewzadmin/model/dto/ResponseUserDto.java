package crewz.admin.crewzadmin.model.dto;

import java.util.List;

import crewz.admin.crewzadmin.model.PagingUtil;
import crewz.admin.crewzadmin.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseUserDto {

	private PagingUtil pagingUtil;
	private List<User> userList;
}
