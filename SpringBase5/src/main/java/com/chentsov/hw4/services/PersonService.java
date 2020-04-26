package com.chentsov.hw4.services;

import com.chentsov.hw4.entities.Person;
import com.chentsov.hw4.repositories.PersonPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonPaginationRepository personRepository;

    @Autowired
    public PersonService(PersonPaginationRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(@NonNull Person person) {
        personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> findById(@NonNull Long id) {
        return personRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Person> findAll(@NonNull Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Person> findAllByAgeBetween(@Nullable Integer minAge, @Nullable Integer maxAge, Pageable pageable) {
        if (minAge != null && maxAge != null) {
            return personRepository.findAllByAgeBetween(minAge, maxAge, pageable);
        }
        if (minAge != null) {
            return personRepository.findAllByAgeGreaterThanEqual(minAge, pageable);
        }
        if (maxAge != null) {
            return personRepository.findAllByAgeLessThanEqual(maxAge, pageable);
        }
        return personRepository.findAll(pageable);
    }

}
