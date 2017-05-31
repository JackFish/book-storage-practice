package kr.bookstorage.dto;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public class UserDto {

	@Data
	public static class Create {
		private String email;
		private String password;
		private String userName;
	}

	@Data
	public static class Update {
		private UUID uniqueId;
		private String password;
		private String userName;
	}

	@Data
	public static class Summary {
		private String email;
		private String userName;
	}

	@Data
	public static class Response {
		private String email;
		private String userName;
		private boolean enabled;
		private DateTime createdDate;
		private DateTime lastModifiedDate;

		private List<UserRoleDto.Response> userRoleList;
	}
}
