package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonPaginationRepository extends PagingAndSortingRepository<Person, Long> {

    Optional<Person> findByFirstNameAndEmail(String firstName, String email);

    @Query("from Person p where p.firstName = :firstName")
    List<Person> filterPersonByName(@Param("firstName") @NonNull String firstName);

    Page<Person> findAllByAgeBetween(@NonNull Integer minAge, @NonNull Integer maxAge, @NonNull Pageable pageable);

    Page<Person> findAllByAgeGreaterThanEqual(@NonNull Integer minAge, @NonNull Pageable pageable);

    Page<Person> findAllByAgeLessThanEqual(@NonNull Integer maxAge, @NonNull Pageable pageable);
}
