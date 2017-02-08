package kr.bookstorage.service.impl;

import kr.bookstorage.domain.Image;
import kr.bookstorage.domain.ImageGroup;
import kr.bookstorage.repository.ImageGroupRepository;
import kr.bookstorage.repository.ImageRepository;
import kr.bookstorage.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("imageService")
@Slf4j
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImageGroupRepository imageGroupRepository;

	@Value("${bookstorage.upload.path}") private String uploadPath;

	@Transactional
	@Modifying
	@Override
	public ImageGroup enabledAuto(ImageGroup imageGroup) {
		if(ObjectUtils.isEmpty(imageGroup)){
			return imageGroup;
		}

		ImageGroup preImageGroup = imageGroupRepository.getOne(imageGroup.getImageGroupIdx());
		List<Image> preImageList = preImageGroup.getImageList();

		List<Image> imageList = imageGroup.getImageList();

		if(preImageGroup.getImageList().size() == imageList.size()){
			return preImageGroup;
		}

		preImageList.forEach(image -> {
			if(ObjectUtils.isEmpty(imageList) || imageList.size() == 0){
				image.setEnabled(false);
			} else {
				boolean update = true;
				for(Image imageUpdate : imageList){
					if(image.getImageIdx() == imageUpdate.getImageIdx()){
						update = false;
					}
				}
				if(update) image.setEnabled(false);
			}
		});

		preImageGroup.setImageList(preImageList);

		return imageGroupRepository.save(preImageGroup);
	}

	@Override
	public Map<String, Object> getImage(long imageIdx) {
		Image image = imageRepository.findOne(imageIdx);

		if(ObjectUtils.isEmpty(image)){
			return null;
		}

		Map<String, Object> map = new HashMap<>();
		map.put("image", image);

		String storedImageName = image.getStoredImageName();

		String fileFullPath = uploadPath
			+ File.separator
			+ storedImageName.substring(0, 4)
			+ File.separator
			+ storedImageName.substring(5, 7)
			+ File.separator
			+ storedImageName.substring(8, 10)
			+ File.separator
			+ storedImageName;

		map.put("file", new File(fileFullPath));

		return map;
	}
}
