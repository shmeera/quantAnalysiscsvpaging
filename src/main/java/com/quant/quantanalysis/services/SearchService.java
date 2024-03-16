package com.quant.quantanalysis.services;

import com.quant.quantanalysis.controllers.Dto.SortValue;
import com.quant.quantanalysis.controllers.Dto.TickerResponseDto;
import com.quant.quantanalysis.models.QuantAnalysis;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.quant.quantanalysis.repositories.QuantAnalysisRepository;

import java.util.List;

@Service
public class SearchService {
    private  QuantAnalysisRepository quantAnalysisRepository;

    public SearchService(QuantAnalysisRepository quantAnalysisRepository) {
        this.quantAnalysisRepository = quantAnalysisRepository;
    }

    public Page<TickerResponseDto> search(String query, int pageNumber, int pageSize) {

        TickerResponseDto tickerResponseDto = new TickerResponseDto();
        //create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        //get the page
        Page<QuantAnalysis> quantAnalysisPage = quantAnalysisRepository.findByTicker(query,pageable);
        //get the list of quantAnalysis
        List<QuantAnalysis> quantAnalysisList = quantAnalysisPage.get().toList();
        tickerResponseDto.setQuantAnalysisList(quantAnalysisList);
        Page<TickerResponseDto> tickerResponseDtoPage = new PageImpl<TickerResponseDto>(List.of(tickerResponseDto),pageable,quantAnalysisPage.getTotalElements());

        return tickerResponseDtoPage;
    }

    public Page<TickerResponseDto> searchLikeTicker(String query, int pageNumber, int pageSize, List<SortValue> sortValues) {
       //create sort object sortValues sortField and sortType
        //initialize sort object
        Sort sort = Sort.unsorted();
       for (SortValue sortValue : sortValues) {
            sort = Sort.by(Sort.Direction.fromString(sortValue.getSortType()), sortValue.getSortField());
         }

        TickerResponseDto tickerResponseDto = new TickerResponseDto();
        //create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        //get the page
        Page<QuantAnalysis> quantAnalysisPage = quantAnalysisRepository.findByTickerContaining(query,pageable);
        //get the list of quantAnalysis
        List<QuantAnalysis> quantAnalysisList = quantAnalysisPage.get().toList();
        tickerResponseDto.setQuantAnalysisList(quantAnalysisList);
        Page<TickerResponseDto> tickerResponseDtoPage = new PageImpl<TickerResponseDto>
                (List.of(tickerResponseDto),pageable,quantAnalysisPage.getTotalElements());

        return tickerResponseDtoPage;
    }

}
