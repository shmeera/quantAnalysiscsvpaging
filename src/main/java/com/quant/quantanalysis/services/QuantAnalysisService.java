package com.quant.quantanalysis.services;

import com.quant.quantanalysis.controllers.Dto.TickerDto;
import com.quant.quantanalysis.controllers.Dto.TickerResponseDto;

import com.quant.quantanalysis.controllers.QuantAnalysisController;
import com.quant.quantanalysis.models.QuantAnalysis;
import com.quant.quantanalysis.repositories.QuantAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuantAnalysisService {
    private QuantAnalysisRepository quantAnalysisRepository;

    @Autowired
    public QuantAnalysisService(QuantAnalysisRepository quantAnalysisRepository) {
        this.quantAnalysisRepository = quantAnalysisRepository;
    }
    public TickerResponseDto getHistoricDataByTicker(String ticker) {
        TickerResponseDto tickerResponseDto = new TickerResponseDto();
        tickerResponseDto.setQuantAnalysisList(quantAnalysisRepository.findByTicker(ticker));
        return tickerResponseDto;
    }
    public TickerResponseDto getAllTickers() {
        TickerResponseDto tickerResponseDto = new TickerResponseDto();
        tickerResponseDto.setQuantAnalysisList(quantAnalysisRepository.findAll());
        return tickerResponseDto;
    }
    public List<Map<String, Object>> getHistoricDataByTicker(String ticker,String columns, int years) {
        List<QuantAnalysis> quantAnalysisList = quantAnalysisRepository.findByTickerAndDate(ticker,years);
        String[] reqColumn = columns.split(",");
        List<TickerDto> tickerDtoList = new ArrayList<>();
        List<Map<String, Object>> mapFields = new ArrayList<>();

        //  TickerDto tickerDto = new TickerDto();
        for (QuantAnalysis quantAnalysis : quantAnalysisList) {
            Map<String, Object> mapField = new HashMap<>();
            for (String s : reqColumn) {
                if (s.equals("ticker"))
                    mapField.put("ticker", quantAnalysis.getTicker());
                else if (s.equals("revenue"))
                    mapField.put("revenue", quantAnalysis.getRevenue());
                else if (s.equals("gp"))
                    mapField.put("gp", quantAnalysis.getGp());
                else  if (s.equals("fcf"))
                    mapField.put("fcf", quantAnalysis.getFcf());
                else if (s.equals("capex"))
                    mapField.put("capex", quantAnalysis.getCapex());
                else if (s.equals("date"))
                    mapField.put("date", quantAnalysis.getDate());

            }
            mapFields.add(mapField);
        }
        return mapFields;
    }

//    private List<Map<String, Object>> convertToDto(List<QuantAnalysis> quantAnalysisList, String columns) {
//        String[] reqColumn = columns.split(",");
//        List<TickerDto> tickerDtoList = new ArrayList<>();
//        List<Map<String, Object>> mapFields = new ArrayList<>();

//      //  TickerDto tickerDto = new TickerDto();
//        for(QuantAnalysis quantAnalysis : quantAnalysisList){
//
//            for(String s: reqColumn) {
//                Map<String, Object> mapField = new HashMap<>();
//                if (s.equals("ticker"))
//                    mapField.put("ticker",quantAnalysis.getTicker());
//                if(s.equals("revenue"))
//                    mapField.put("revenue",quantAnalysis.getRevenue());
//                if(s.equals("gp"))
//                    mapField.put("gp",quantAnalysis.getGp());
//                mapFields.add(mapField);
//            }
//
//            TickerDto tickerDto = new TickerDto();
//            for(String s: reqColumn) {
//                if(s.equals("ticker"))
//                    tickerDto.setTicker(quantAnalysis.getTicker());
//                if(s.equals("revenue"))
//                    tickerDto.setRevenue(quantAnalysis.getRevenue());
//                if(s.equals("gp"))
//                    tickerDto.setGp(quantAnalysis.getGp());
//                if(s.equals("date"))
//                    tickerDto.setDate(quantAnalysis.getDate());
//            }
//            tickerDtoList.add(tickerDto);
//        }
//        return mapFields;
   // }
}
