package kr.bookstorage.dto;

import kr.bookstorage.domain.code.DeviceOs;
import lombok.Data;

public class DeviceDto {
	@Data
	public static class Create {
		private String token;

		private DeviceOs deviceOs;

		private boolean noticeOn;
	}

	@Data
	public static class Response {
		private Long idx;

		private String token;

		private DeviceOs deviceOs;

		private boolean noticeOn;
	}

	@Data
	public static class Update {
		private Long idx;

		private String token;

		private DeviceOs deviceOs;

		private boolean noticeOn;
	}
}
