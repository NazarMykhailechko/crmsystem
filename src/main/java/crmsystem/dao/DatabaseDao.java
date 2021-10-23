package crmsystem.dao;

import crmsystem.dto.Clients;
import crmsystem.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatabaseDao extends JpaRepository<Database, String> {

}