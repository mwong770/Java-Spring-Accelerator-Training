package com.company.mariawongu1capstone.dao;

import java.math.BigDecimal;

public interface SalesTaxRateDao {

    // needed to calculate invoice total
    BigDecimal getSalesTaxRate(String state);

}
