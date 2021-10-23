package crmsystem.dao;

import crmsystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoanDao extends JpaRepository<Loan, Integer> {
}