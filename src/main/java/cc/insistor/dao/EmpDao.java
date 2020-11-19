package cc.insistor.dao;

import cc.insistor.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 */
public interface EmpDao extends JpaRepository<Emp,Integer> {
}
