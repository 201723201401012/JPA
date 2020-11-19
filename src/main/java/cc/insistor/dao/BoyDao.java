package cc.insistor.dao;

import cc.insistor.domain.Boy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 */
public interface BoyDao extends JpaRepository<Boy,Integer> {
}
