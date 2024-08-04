package com.company.asyncstatistic.repository;

import com.company.asyncstatistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    Optional<Statistic> findByCategoryId(Long categoryId);
}
