package test.codingassignment.developer.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.codingassignment.developer.registration.entity.DeveloperRegistrationEntity;

@Repository
public interface DeveloperRegistrationRepository extends JpaRepository<DeveloperRegistrationEntity, String> {
}
