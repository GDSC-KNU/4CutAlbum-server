package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{

    boolean existsAllByvalueIn(String[] value);

    Optional<Hashtag> findByvalue(String value);
}