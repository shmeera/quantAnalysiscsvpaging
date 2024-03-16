package com.quant.quantanalysis.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private  String query;
    private int pageNumber;
    private int pageSize;
    private List<SortValue> sortValues;
}
