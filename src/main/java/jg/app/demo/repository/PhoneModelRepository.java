package jg.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneModelRepository extends JpaRepository<PhoneModel, Long> {
}
