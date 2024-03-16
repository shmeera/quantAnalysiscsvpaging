package com.quant.quantanalysis.controllers.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TickerDto {
    private String ticker;
    private double revenue;
    private double gp;
    private Date date;
}
