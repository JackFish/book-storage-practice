package kr.bookstorage.talk;

import kr.bookstorage.talk.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by ksb on 2017. 5. 29..
 */
@Controller
public class TalkController {

    @Autowired
    private TalkService talkServiceImpl;

    public void talkRoom(){

    }

//    @SubscribeMapping("/talk.participants")
//    public Collection<LoginEvent> retrieveParticipants() {
//        return participantRepository.getActiveSessions().values();
//    }
//
//    @MessageMapping("/talk.message")
//    public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal) {
//        checkProfanityAndSanitize(message);
//
////        message.setUsername(principal.getName());
//
//        return message;
//    }

    /*@MessageMapping("/image/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImage(@DestinationVariable String id,
                                                   ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.download(message.getImageIdx(), uuid);
    }

    @MessageMapping("/collection/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByCollection(@DestinationVariable String id,
                                                               ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByCollectionIdx(message.getCollectionIdx(), message.getCollectionPhotoType(), uuid);
    }

    @MessageMapping("/street/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByStreet(@DestinationVariable String id,
                                                           ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByStreetIdx(message.getStreetIdx(), uuid);
    }

    @MessageMapping("/story/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByStory(@DestinationVariable String id,
                                                          ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByStoryIdx(message.getStoryIdx(), uuid);
    }



    @MessageMapping("/brand/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByBrand(@DestinationVariable String id,
                                                          ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByBrandIdx(message.getFashionWeekIdx(), message.getBrandIdx(), message.getCollectionPhotoType(), uuid);
    }

    @MessageMapping("/fashionWeek/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByFashionWeek(@DestinationVariable String id,
                                                                ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByFashionWeekIdx(message.getFashionWeekIdx(), message.getCollectionPhotoType(), uuid);
    }

    @MessageMapping("/fashionWeekForStreet/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByFashionWeekForStreet(@DestinationVariable String id,
                                                                         ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadImageByFashionWeekForStreet(message.getFashionWeekIdx(), message.getStreetIdx(), uuid);
    }

    @MessageMapping("/fashionWeekForStory/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadImageByFashionWeekForStory(@DestinationVariable String id,
                                                                        ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadImageByFashionWeekForStory(message.getFashionWeekIdx(), message.getStoryIdx(), uuid);
    }

    @MessageMapping("/cart/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadCart(@DestinationVariable String id,
                                                  ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByUniqueId(uuid);
    }

    @MessageMapping("/cartDetail/{id}")
    @SendToUser("/image/download/{id}")
    public ImageDownloadDto.Response downloadCartDetail(@DestinationVariable String id,
                                                        ImageDownloadDto.Request message, Authentication authentication) throws Exception {
        UUID uuid = (UUID) authentication.getCredentials();

        return imageDownloadService.downloadByUniqueIdAndIdx(uuid, message);
    }*/

}
