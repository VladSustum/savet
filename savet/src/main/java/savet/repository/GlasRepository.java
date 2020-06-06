package savet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import savet.model.Glas;

@Repository
public interface GlasRepository extends JpaRepository<Glas, Long> {

}
