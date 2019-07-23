package com.company.mariawongu1capstone.dao;

import java.math.BigDecimal;


public interface ProcessingFeeDao {

    // needed to calculate invoice total
    BigDecimal getProcessingFee(String productType);

}
