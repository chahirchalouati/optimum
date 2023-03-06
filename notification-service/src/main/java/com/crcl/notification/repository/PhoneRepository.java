package com.crcl.notification.repository;

import com.crcl.notification.domain.Phone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends MongoRepository<Phone, String> {
}