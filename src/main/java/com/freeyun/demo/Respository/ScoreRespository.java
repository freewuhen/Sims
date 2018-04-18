package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Scores;
import com.freeyun.demo.Domain.ScoreMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRespository extends JpaRepository<Scores,ScoreMultiKeys> {

}
