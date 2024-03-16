package com.quant.quantanalysis.controllers;

import com.quant.quantanalysis.controllers.Dto.TickerDto;
import com.quant.quantanalysis.controllers.Dto.TickerRequestDto;
import com.quant.quantanalysis.controllers.Dto.TickerResponseDto;

import com.quant.quantanalysis.exceptions.NotFoundException;
import com.quant.quantanalysis.services.QuantAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuantAnalysisController {
    private QuantAnalysisService quantAnalysisService;
    @Autowired
    public QuantAnalysisController(QuantAnalysisService quantAnalysisService) {
        this.quantAnalysisService = quantAnalysisService;
    }
    @GetMapping("/tickers")
    public ResponseEntity<TickerResponseDto>  getAllTickers() {
        TickerResponseDto tickerResponseDto = quantAnalysisService.getAllTickers();
        return new ResponseEntity<>(tickerResponseDto, HttpStatus.OK);
    }
    @GetMapping("/ticker")
    public ResponseEntity<TickerResponseDto>  getHistoricDataByTicker(@RequestBody TickerRequestDto request) throws NotFoundException {
        TickerResponseDto tickerResponseDto = quantAnalysisService.getHistoricDataByTicker(request.getTicker());
        if(tickerResponseDto.getQuantAnalysisList().isEmpty())
        {
            throw  new NotFoundException("Tricker "+request +"does not exist");
        }
        return new ResponseEntity<>(tickerResponseDto, HttpStatus.OK);
    }
//    @GetMapping("/ticker/{request}")
//    public ResponseEntity<TickerResponseDto>  getHistoricDataByTicker(@PathVariable("request")  String request) {
//        TickerResponseDto tickerResponseDto = quantAnalysisService.getHistoricDataByTicker(request);
//        return new ResponseEntity<>(tickerResponseDto, HttpStatus.OK);
//    }

    @GetMapping("/tickerparam")
    public ResponseEntity<List<Map<String, Object>>>  getHistoricDataByTicker(@RequestParam  String ticker,
                                                                              @RequestParam String columns,
                                                                              @RequestParam int years) {
        List<Map<String, Object>> tickerDtoList = quantAnalysisService.getHistoricDataByTicker(ticker,columns,years);

        return new ResponseEntity<>(tickerDtoList, HttpStatus.OK);
    }
}
