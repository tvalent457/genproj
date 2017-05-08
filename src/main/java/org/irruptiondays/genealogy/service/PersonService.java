package org.irruptiondays.genealogy.service;

import org.irruptiondays.genealogy.dao.PersonRepository;
import org.irruptiondays.genealogy.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tvalentine on 5/8/2017.
 */
@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Set<Person> getSiblingsByPerson(Person person) {
        Person personFromDb = personRepository.findOne(person.getId());
        Set<Person> siblings = new HashSet<>();

        if (personFromDb.getFather() != null) {
            siblings.addAll(personRepository.getChildrenOfPerson(personFromDb.getFather()));
        }

        if (personFromDb.getMother() != null) {
            siblings.addAll(personRepository.getChildrenOfPerson(personFromDb.getMother()));
        }

        // Should always be true, but checking anyway
        if (siblings.contains(person)) {
            siblings.remove(person);
        }

        return siblings;
    }


}
