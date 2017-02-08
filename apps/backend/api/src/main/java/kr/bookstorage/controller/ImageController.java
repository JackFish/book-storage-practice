package kr.bookstorage.controller;

import kr.bookstorage.domain.Image;
import kr.bookstorage.service.ImageService;
import kr.bookstorage.util.FileSenderUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Resource(name = "imageService")
	private ImageService imageService;

	@GetMapping("/{imageIdx:\\d+}-{imageName}")
	public void downImg(
		@PathVariable("imageIdx") long imageIdx,
		@PathVariable("imageName") String imageName,
		HttpServletRequest request, HttpServletResponse response){

		fileDownload(imageIdx, imageName, request, response);
	}

	@GetMapping("/thumb/{imageIdx:\\d+}-{imageName}")
	public void downThumbImg(
		@PathVariable("imageIdx") long imageIdx,
		@PathVariable("imageName") String imageName,
		HttpServletRequest request, HttpServletResponse response){

		fileDownload(imageIdx, imageName, request, response);
	}

	private void fileDownload(long imageIdx, String imageName, HttpServletRequest request, HttpServletResponse response) {
		try {

			Map<String, Object> map = imageService.getImage(imageIdx);
			File file = (File) map.get("file");
			Image image = (Image) map.get("image");

			FileSenderUtil.fromFile(file)
				.with(request)
				.with(response)
				.with(MediaType.valueOf(image.getContentType()))
				.with(imageName)
				.serveResource();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
