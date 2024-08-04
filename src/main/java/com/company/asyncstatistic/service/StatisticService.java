package com.company.asyncstatistic.service;

import com.company.asyncstatistic.exception.CategoryNotFoundException;
import com.company.commoneventlib.event.ProductEvent;

public interface StatisticService {

    void updateStatistics(ProductEvent productEvent) throws CategoryNotFoundException;
}
