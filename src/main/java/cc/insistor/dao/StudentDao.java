package cc.insistor.dao;

import cc.insistor.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 *
 */
public interface StudentDao extends JpaRepository<Student,Integer> {
}
