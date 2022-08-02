package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    public static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPeopleById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePeopleById(UUID id) {
        Optional<Person> optionalPerson = selectPeopleById(id);
        if(optionalPerson.isEmpty()){
            return 0;
        }
        DB.remove(optionalPerson.get());
        return 1;
    }

    @Override
    public void updatePeopleById(UUID id, Person person) {
         selectPeopleById(id)
                .map(p -> {
                    int updatePersonIdx = DB.indexOf(p);
                    if(updatePersonIdx >= 0) {
                        DB.set(updatePersonIdx, new Person(id, person.getName()));
                        return 1;
                    }else{
                        return 0;
                    }
                }).orElse(0);
    }
}
