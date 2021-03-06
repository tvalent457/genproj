package org.irruptiondays.genealogy.dao;

import org.irruptiondays.genealogy.common.FamilyBranch;
import org.irruptiondays.genealogy.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Repository for person CRUD.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * Custom query to get all children of a person.
     * @param person
     * @return Set of persons
     */
    @Query("SELECT p FROM Person p WHERE p.father = :person OR p.mother = :person")
    Set<Person> getChildrenOfPerson(@Param("person") Person person);

    /**
     * Get all persons from a particular Family Branch
     * @param familyBranch
     * @return Set of persons
     */
    Set<Person> getPersonByFamilyBranch(FamilyBranch familyBranch);

    /**
     * Returns alphabetical listing of all people in database
     * @return Set of ordered persons
     */
    @Query("SELECT p FROM Person p order by p.lastName, p.firstName")
    Set<Person> getAllPersonsOrderByLastNameAscFirstNameAsc();


}
