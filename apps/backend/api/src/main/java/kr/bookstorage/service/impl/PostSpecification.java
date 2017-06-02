package kr.bookstorage.service.impl;

import kr.bookstorage.dto.PostDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksb on 16. 11. 14.
 */
public class PostSpecification implements Specification<PostDto.Search> {

    private PostDto.Search search;

    public PostSpecification(PostDto.Search search){
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<PostDto.Search> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        final List<Predicate> predicates = new ArrayList<>();
        /*if( search.getType() != null ) {
            String type = search.getType().getCode().toLowerCase();
            Path<String> name = root.get(type);
            predicates.add(cb.like(name, "%"+search.getTerm()+"%"));
        }

        Path<String> name = root.get("enabled");
        predicates.add(cb.equal(name, search.isEnabled()));*/

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
