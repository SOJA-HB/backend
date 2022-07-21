package fr.shewbii.sojahbbackend.repository;

import fr.shewbii.sojahbbackend.domain.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {
}
