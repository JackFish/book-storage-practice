package kr.bookstorage.service.impl;

import kr.bookstorage.domain.BookRecord;
import kr.bookstorage.dto.BookRecordDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksb on 16. 11. 14.
 */
public class BookRecordSpecification implements Specification<BookRecordDto.Search> {

    private BookRecordDto.Search search;

    public BookRecordSpecification(BookRecordDto.Search search){
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<BookRecordDto.Search> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        final List<Predicate> predicates = new ArrayList<>();
        if( search.getType() != null ) {
            String type = search.getType().getCode().toLowerCase();
            Path<String> name = root.get(type);
            predicates.add(cb.like(name, "%"+search.getTerm()+"%"));
        }

        Path<String> name = root.get("enabled");
        predicates.add(cb.equal(name, search.isEnabled()));

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
