package cc.insistor.dao;

import cc.insistor.domain.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 */
public interface ClazzDao extends JpaRepository<Clazz,Integer> {
}
