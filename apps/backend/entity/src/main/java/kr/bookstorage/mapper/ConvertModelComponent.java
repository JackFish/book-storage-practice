package kr.bookstorage.mapper;

import kr.bookstorage.domain.*;
import kr.bookstorage.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ksb on 16. 11. 20.
 */
@Component
public class ConvertModelComponent {

    @Value("${bookstorage.upload.path}")
    private String uploadPath;

    @Autowired
    private ModelMapper modelMapper;

    public ModelMapper getModelMapper(){
        return modelMapper;
    }

    public BookReportDto.Summary convertToSummaryDto(BookReport bookReport) {
        BookReportDto.Summary bookReportDto = new BookReportDto.Summary();
        BookRecordDto.Summary bookRecordDto = null;
        if(bookReport.getBookRecord() != null){
            bookRecordDto = modelMapper.map(bookReport.getBookRecord(), BookRecordDto.Summary.class);
        }
        List<BlockDto.Summary> blockDtoList = bookReport.getBlockList().stream().map(block -> {
            if(block instanceof DraftBlock){
                return modelMapper.map(block, DraftBlockDto.Summary.class);
            } else if(block instanceof ImageBlock){
                return modelMapper.map(block, ImageBlockDto.Summary.class);
            } else if(block instanceof YoutubeBlock){
                return modelMapper.map(block, YoutubeBlockDto.Summary.class);
            } else {
                return null;
            }
        }).collect(toList());
        List<TagDto> tagList = convertToTagDtoList(bookReport.getTagList());

        bookReportDto.setIdx(bookReport.getIdx());
        bookReportDto.setSubject(bookReport.getSubject());
        bookReportDto.setVisible(bookReport.isVisible());
        bookReportDto.setBookRecord(bookRecordDto);
        bookReportDto.setBlockList(blockDtoList);
        bookReportDto.setTagList(tagList);

        return bookReportDto;
    }

    public BookReportDto.Detail convertToDetailDto(BookReport bookReport) {
        BookReportDto.Detail bookReportDto = new BookReportDto.Detail();
        BookRecordDto.Refer bookRecordDto = null;
        if(bookReport.getBookRecord() != null){
            bookRecordDto = modelMapper.map(bookReport.getBookRecord(), BookRecordDto.Refer.class);
        }
        List<BlockDto.Detail> blockDtoList = bookReport.getBlockList().stream().map(block -> {
            if(block instanceof DraftBlock){
                return modelMapper.map(block, DraftBlockDto.Detail.class);
            } else if(block instanceof ImageBlock){
                return modelMapper.map(block, ImageBlockDto.Detail.class);
            } else if(block instanceof YoutubeBlock){
                return modelMapper.map(block, YoutubeBlockDto.Detail.class);
            } else {
                return null;
            }
        }).collect(toList());
        List<TagDto> tagList = convertToTagDtoList(bookReport.getTagList());

        bookReportDto.setIdx(bookReport.getIdx());
        bookReportDto.setSubject(bookReport.getSubject());
        bookReportDto.setVisible(bookReport.isVisible());
        bookReportDto.setBookRecord(bookRecordDto);
        bookReportDto.setBlockList(blockDtoList);
        bookReportDto.setTagList(tagList);

        return bookReportDto;
    }

    public BookRecord convertToBookRecord(BookRecordDto.Refer bookRecordDto) {
        if(bookRecordDto != null){
            return modelMapper.map(bookRecordDto, BookRecord.class);
        } else {
            return null;
        }
    }

    public List<TagDto> convertToTagDtoList(List<Tag> tagList) {
        return tagList.stream().map(tag -> modelMapper.map(tag, TagDto.class)).collect(toList());
    }

    public Block convertToBlock(BlockDto.Create blockDto){
        if(blockDto instanceof DraftBlockDto.Create){
            return modelMapper.map(blockDto, DraftBlock.class);
        } else if(blockDto instanceof ImageBlockDto.Create){
            ImageBlock imageBlock = modelMapper.map(blockDto, ImageBlock.class);
            for(Image image : imageBlock.getImageGroup().getImageList()){
                image.setImageGroup(imageBlock.getImageGroup());
                image.setUploadPath(uploadPath);
            }
            return imageBlock;
        } else if(blockDto instanceof YoutubeBlockDto.Create){
            return modelMapper.map(blockDto, YoutubeBlock.class);
        } else {
            return null;
        }
    }

    public Block convertToBlock(BlockDto.Update blockDto){
        if(blockDto instanceof DraftBlockDto.Update){
            return modelMapper.map(blockDto, DraftBlock.class);
        } else if(blockDto instanceof ImageBlockDto.Update){
            ImageBlock imageBlock = modelMapper.map(blockDto, ImageBlock.class);
            for(Image image : imageBlock.getImageGroup().getImageList()){
                image.setImageGroup(imageBlock.getImageGroup());
                image.setUploadPath(uploadPath);
            }
            return imageBlock;
        } else if(blockDto instanceof YoutubeBlockDto.Update){
            return modelMapper.map(blockDto, YoutubeBlock.class);
        } else {
            return null;
        }
    }

    public List<Tag> convertToTagList(List<TagDto> tagDtoList) {
        return tagDtoList.stream().map(tagDto -> modelMapper.map(tagDto, Tag.class)).collect(toList());
    }

    public ImageGroup convertToImageGroup(ImageGroupDto.Update imageGroupDto) {
        if(imageGroupDto != null){
            ImageGroup imageGroup = modelMapper.map(imageGroupDto, ImageGroup.class);
            for(Image image : imageGroup.getImageList()){
                image.setImageGroup(imageGroup);
                image.setUploadPath(uploadPath);
            }
            return imageGroup;
        } else {
            return null;
        }
    }

}
