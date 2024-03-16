package com.quant.quantanalysis.controllers.Dto;


import com.quant.quantanalysis.models.QuantAnalysis;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TickerResponseDto {
   private List<QuantAnalysis> quantAnalysisList;
}
