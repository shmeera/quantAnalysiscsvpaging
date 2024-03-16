package com.quant.quantanalysis.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "quantanalysis")
public class QuantAnalysis{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;
    private Date date;
    @Column(columnDefinition = "DECIMAL")
    private Double revenue;
    @Column(columnDefinition = "DECIMAL")
    private Double gp;
    @Column(columnDefinition = "DECIMAL")
    private Double fcf;
    @Column(columnDefinition = "DECIMAL")
    private Double capex;


}
