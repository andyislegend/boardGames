package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softserveinc.edu.boardgames.persistence.entity.Address;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
