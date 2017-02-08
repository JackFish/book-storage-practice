package kr.bookstorage.service.impl;

import kr.bookstorage.domain.BookRecord;
import kr.bookstorage.domain.Image;
import kr.bookstorage.dto.BookRecordDto;
import kr.bookstorage.mapper.ConvertModelComponent;
import kr.bookstorage.repository.BookRecordRepository;
import kr.bookstorage.repository.ImageRepository;
import kr.bookstorage.service.BookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by ksb on 16. 11. 9.
 */
@Service
public class BookRecordServiceImpl implements BookRecordService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ConvertModelComponent convertModelComponent;

    @Autowired
    private BookRecordRepository bookRecordRepository;

    @Override
    public List<BookRecordDto.Summary> findSummaryList() {
        return StreamSupport.stream(bookRecordRepository.findAll(new Sort(Sort.Direction.DESC, "idx")).spliterator(), false)
                .map(bookRecord -> convertModelComponent.getModelMapper().map(bookRecord, BookRecordDto.Summary.class))
                .collect(toList());
    }

    @Override
    public Page<BookRecordDto.Summary> findSummaryList(Pageable pageable) {
        Page<BookRecord> page = bookRecordRepository.findAll(pageable);
        List<BookRecordDto.Summary> content = page.getContent().stream()
                .map(bookRecord -> convertModelComponent.getModelMapper().map(bookRecord, BookRecordDto.Summary.class)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public Page<BookRecordDto.Summary> findSummaryList(Pageable pageable, BookRecordDto.Search search) {
        Specification<BookRecordDto.Search> searchSpecification = new BookRecordSpecification(search);
        Page<BookRecord> page = bookRecordRepository.findAll(searchSpecification, pageable);
        List<BookRecordDto.Summary> content = page.getContent().stream()
                .map(bookRecord -> convertModelComponent.getModelMapper().map(bookRecord, BookRecordDto.Summary.class)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public BookRecordDto.Detail findDetailOne(Long idx) {
        return convertModelComponent.getModelMapper().map(bookRecordRepository.findOne(idx), BookRecordDto.Detail.class);
    }

    @Override
    public void create(BookRecordDto.Create bookRecordDto) {
        BookRecord result = new BookRecord();
        result.convert(bookRecordDto, convertModelComponent);
        bookRecordRepository.save(result);
    }

    @Override
    @Transient
    public void update(BookRecordDto.Update bookRecord) {
        BookRecord origin = bookRecordRepository.findOne(bookRecord.getIdx());
        origin.merge(bookRecord, convertModelComponent);

        for(Image image : origin.getImageGroup().getImageList()){
            imageRepository.save(image);
        }

        bookRecordRepository.save(origin);
    }

    @Transactional
    @Override
    public void visible(BookRecordDto.Visible bookRecord) {
        bookRecordRepository.updateVisible(bookRecord.getIdx(),bookRecord.isVisible());
    }

    @Transactional
    @Override
    public void remove(Long idx) {
        if(bookRecordRepository.exists(idx)){
            bookRecordRepository.remove(idx);
        }
    }

    @Override
    public boolean existSubject(String subject) {
        BookRecord bookRecord = bookRecordRepository.findBySubject(subject);

        if(!ObjectUtils.isEmpty(bookRecord)){
            return true;
        }
        return false;
    }

}