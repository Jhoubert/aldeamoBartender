package com.aldeamo.BartenderTest.service;
import com.aldeamo.BartenderTest.entity.BartenderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface BartenderRepository extends CrudRepository<BartenderEntity, Long> {
}