package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class FilterSpecification implements Specification<Clinic> {

    private String location;
    private String nameInsurance;
    private String specialty;

    @Override
    public Predicate toPredicate(Root<Clinic> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

        if (location != null) {
            predicates.add(criteriaBuilder.equal(
                    root.join("clincLocations").get("countryName"),
                    location
            ));
        }

        if (nameInsurance != null) {
            predicates.add(criteriaBuilder.equal(
                    root.join("insurances").get("insuranceName"),
                    nameInsurance
            ));
        }

        if (specialty != null) {
            predicates.add(criteriaBuilder.equal(
                    root.join("specialities").get("speciality_name"), specialty)

            );

        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
