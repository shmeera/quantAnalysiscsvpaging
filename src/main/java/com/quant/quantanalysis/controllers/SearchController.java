package com.quant.quantanalysis.controllers;

import com.quant.quantanalysis.controllers.Dto.SearchRequestDto;
import com.quant.quantanalysis.controllers.Dto.TickerResponseDto;
import com.quant.quantanalysis.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<TickerResponseDto> search(@RequestBody SearchRequestDto request) {
        //return  searchService.search(request.getQuery(), request.getPageNumber(), request.getPageSize());
        return  searchService.searchLikeTicker(request.getQuery(), request.getPageNumber(), request.getPageSize(),request.getSortValues());
    }
}
