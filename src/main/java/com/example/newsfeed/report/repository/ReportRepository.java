package com.example.newsfeed.report.repository;

import com.example.newsfeed.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    int countByBoardId(Long boardId);
}