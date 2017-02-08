package kr.bookstorage.service.impl;

import kr.bookstorage.domain.BookReport;
import kr.bookstorage.domain.Image;
import kr.bookstorage.domain.ImageBlock;
import kr.bookstorage.dto.BookReportDto;
import kr.bookstorage.mapper.ConvertModelComponent;
import kr.bookstorage.repository.BookReportRepository;
import kr.bookstorage.repository.ImageRepository;
import kr.bookstorage.service.BookReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by ksb on 16. 11. 9.
 */
@Service
public class BookReportServiceImpl implements BookReportService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ConvertModelComponent convertModelComponent;

    @Autowired
    private BookReportRepository bookReportRepository;

    @Override
    public List<BookReportDto.Summary> findSummaryList() {
        return StreamSupport.stream(bookReportRepository.findAll(new Sort(Sort.Direction.DESC, "idx")).spliterator(), false)
                .map(bookReport -> convertModelComponent.convertToSummaryDto(bookReport))
                .collect(toList());
    }

    @Override
    public Page<BookReportDto.Summary> findSummaryList(Pageable pageable) {
        Page<BookReport> page = bookReportRepository.findAll(pageable);
        List<BookReportDto.Summary> content = page.getContent().stream()
                .map(bookReport -> convertModelComponent.convertToSummaryDto(bookReport)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public Page<BookReportDto.Summary> findSummaryList(Pageable pageable, BookReportDto.Search search) {
        Specification<BookReportDto.Search> searchSpecification = new BookReportSpecification(search);
        Page<BookReport> page = bookReportRepository.findAll(searchSpecification, pageable);
        List<BookReportDto.Summary> content = page.getContent().stream()
                .map(bookReport -> convertModelComponent.convertToSummaryDto(bookReport)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public BookReportDto.Detail findDetailOne(Long idx) {
        return convertModelComponent.convertToDetailDto(bookReportRepository.findOne(idx));
    }

    @Override
    public void create(BookReportDto.Create bookReportDto) {
        BookReport result = new BookReport();
        result.convert(bookReportDto, convertModelComponent);

        bookReportRepository.save(result);
    }

    @Transactional
    @Override
    public void update(BookReportDto.Update bookReportDto) {
        BookReport origin = bookReportRepository.findOne(bookReportDto.getIdx());
        origin.merge(bookReportDto, convertModelComponent);

        origin.getBlockList().stream().forEach(block -> {
            if(block instanceof ImageBlock) {
                ImageBlock imageBlock = (ImageBlock)block;
                for(Image image : imageBlock.getImageList()){
                    imageRepository.save(image);
                }
            }
        });

        bookReportRepository.save(origin);
    }

    @Transactional
    @Override
    public void visible(BookReportDto.Visible bookReportDto) {
        bookReportRepository.updateVisible(bookReportDto.getIdx(), bookReportDto.isVisible());
    }

    @Transactional
    @Override
    public void remove(Long idx) {
        if(bookReportRepository.exists(idx)){
            bookReportRepository.remove(idx);
        }
    }

}
