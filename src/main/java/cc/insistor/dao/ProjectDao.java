package cc.insistor.dao;

import cc.insistor.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cc
 */
public interface ProjectDao extends JpaRepository<Project,Integer> {
}
