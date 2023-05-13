package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    //全件検索
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }
    //一件検索
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }
    //レコード数のカウント
    public long index() {
        return reportRepository.count();
    }

    //登録
    public Report saveReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());

        return reportRepository.save(report);
    }
    //更新
    public Report updateReport(Report report) {
        report.setUpdatedAt(LocalDateTime.now());

        return reportRepository.save(report);
    }



}
