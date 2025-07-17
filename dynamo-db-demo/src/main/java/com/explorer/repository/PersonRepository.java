package com.explorer.repository;

import com.explorer.entity.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
//@Slf4j
public class PersonRepository {

    @Autowired
    private DynamoDbEnhancedClient mapper;

    private DynamoDbTable<Person> personTable;

    @PostConstruct
    public void init(){
        this.personTable = mapper.table("person", TableSchema.fromBean(Person.class));
    }

    public void savePerson(Person person){
        //log.info("person details saved successfully...");
        personTable.putItem(person);
    }

    public Person getPersonById(String personId){
        System.out.println("personTable: "+personTable);
        return personTable.getItem(r -> r.key(k -> k.partitionValue(personId)));
    }

    public Person updatePerson(Person person){
        //log.info("person details updated successfully...");
        return personTable.updateItem(person);
    }

    public Person deletePerson(String personId){
        //log.info("person details removed successfully...");
        return personTable.deleteItem(r -> r.key(k -> k.partitionValue(personId)));
    }
}
