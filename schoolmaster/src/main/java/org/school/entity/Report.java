package org.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reportType; // ATTENDANCE_REPORT, GRADE_REPORT, FEE_REPORT

    @ManyToOne
    @JoinColumn(name = "generated_by", nullable = false)
    private User generatedBy; // References Admin (User)

    @Column(nullable = false)
    private LocalDate generatedAt = LocalDate.now(); // Auto-sets to current date

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public User getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(User generatedBy) {
		this.generatedBy = generatedBy;
	}

	public LocalDate getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDate generatedAt) {
		this.generatedAt = generatedAt;
	}

	public Report(Long id, String reportType, User generatedBy, LocalDate generatedAt) {
		super();
		this.id = id;
		this.reportType = reportType;
		this.generatedBy = generatedBy;
		this.generatedAt = generatedAt;
	}

	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("Report [id=%s, reportType=%s, generatedBy=%s, generatedAt=%s]", id, reportType,
				generatedBy, generatedAt);
	}

    
}
