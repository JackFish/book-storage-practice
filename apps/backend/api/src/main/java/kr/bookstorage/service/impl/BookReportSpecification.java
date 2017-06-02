package kr.bookstorage.service.impl;

import kr.bookstorage.domain.code.BookReportSearchStatus;
import kr.bookstorage.dto.BookReportDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksb on 16. 11. 16.
 */
public class BookReportSpecification implements Specification<BookReportDto.Search> {

    private BookReportDto.Search search;

    public BookReportSpecification(BookReportDto.Search search){
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<BookReportDto.Search> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();
        if( search.getType() != null ) {
            if(search.getType().equals(BookReportSearchStatus.SUBJECT)){
                String type = search.getType().getCode().toLowerCase();
                Path<String> name = root.get(type);
                predicates.add(cb.like(name, "%" + search.getTerm() + "%"));
            } else if(search.getType().equals(BookReportSearchStatus.BOOK_RECORD_SUBJECT)){
                Path<String> subject = root.join("bookRecord").get("subject");
                predicates.add(cb.like(subject, "%" + search.getTerm() + "%"));
            } else if(search.getType().equals(BookReportSearchStatus.TAG)){
                Path<String> tag = root.join("tagList").get("text");
                predicates.add(cb.like(tag, "%" + search.getTerm() + "%"));
            }
        }

        Path<String> name = root.get("enabled");
        predicates.add(cb.equal(name, search.isEnabled()));

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
