package kr.bookstorage.service;

import kr.bookstorage.domain.ImageGroup;

import java.util.Map;

public interface ImageService {

	/**
	 * 자동으로 목록에 없는 image 를 enabled -> false 처리
	 * @param imageGroup 이미지 그룹 Entity
	 * @return enabled false 처리 완료 된 ImageGroup
	 */
	ImageGroup enabledAuto(ImageGroup imageGroup);

	/**
	 * 이미지 PK 에 맞는 파일을 읽어서 가지고 온다.
	 * @param imageIdx imageIdx
	 * @return file : 해당 파일, image : Image 도메인 ( 이미지 디비 정보 )
	 */
	Map<String, Object> getImage(long imageIdx);
}
