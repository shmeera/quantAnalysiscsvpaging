package com.quant.quantanalysis.repositories;


import com.quant.quantanalysis.controllers.Dto.TickerDto;
import com.quant.quantanalysis.models.QuantAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuantAnalysisRepository extends JpaRepository<QuantAnalysis, Long>{
    List<QuantAnalysis> findByTicker(String ticker);
    Page<QuantAnalysis> findByTicker(String ticker, Pageable pageable);
    Page<QuantAnalysis> findByTickerContaining(String ticker, Pageable pageable);//its like operator
    //we also need metadata for for that wrap it in  Page
    @Query(value = "select * from quantanalysis where ticker = :ticker and date > CURRENT_DATE - INTERVAL :years YEAR", nativeQuery = true)
    List<QuantAnalysis> findByTickerAndDate(@Param("ticker") String ticker, @Param("years") int years);

//    @Query(value = "SELECT new com.quant.quantanalysis.models.QuantAnalysis(q.ticker, q.revenue,q.gp) FROM quantanalysis q WHERE q.ticker = :ticker ")
//    List<QuantAnalysis> findByTicker(@Param("ticker") String ticker);
}
