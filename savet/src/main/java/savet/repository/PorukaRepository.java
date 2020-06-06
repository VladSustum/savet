package savet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import savet.model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {
	
	@Query("SELECT p FROM Poruka p WHERE "
			+ "(:zgrada IS NULL OR p.zgrada.id LIKE :zgrada) AND "
			+ "(:naslov IS NULL OR p.naslov LIKE :naslov) AND "
			+ "(:tip IS NULL OR p.tip LIKE :tip)"
			)
	Page<Poruka> search(
			@Param("zgrada") Long zgrada,
			@Param("naslov") String naslov,
			@Param("tip") String tip,
			Pageable pageRequest
			);

}
