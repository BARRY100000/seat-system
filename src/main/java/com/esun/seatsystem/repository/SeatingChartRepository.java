package com.esun.seatsystem.repository;

import com.esun.seatsystem.entity.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Long> {
}