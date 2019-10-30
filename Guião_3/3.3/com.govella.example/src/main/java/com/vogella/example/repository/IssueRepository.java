package com.vogella.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.example.entity.IssueReport;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueReport, Long>{
    @Query(value = "SELECT i FROM IssueReport i WHERE markedAsPrivate = false")
    List<IssueReport> findAllButPrivate();
    List<IssueReport> findAllByEmail(String email);

}