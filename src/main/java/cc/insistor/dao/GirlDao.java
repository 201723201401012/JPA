package cc.insistor.dao;

import cc.insistor.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 */
public interface GirlDao extends JpaRepository<Girl,Integer> {
}
