package com.vacinas.ap1.repository;

import com.vacinas.ap1.entity.Vacina;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacinaRepository extends MongoRepository<Vacina,String> {

}
