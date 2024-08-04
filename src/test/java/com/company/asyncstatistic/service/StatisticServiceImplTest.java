package com.company.asyncstatistic.service;

import com.company.asyncstatistic.entity.Statistic;
import com.company.asyncstatistic.exception.CategoryNotFoundException;
import com.company.asyncstatistic.repository.StatisticRepository;
import com.company.asyncstatistic.service.impl.StatisticServiceImpl;
import com.company.commoneventlib.enums.ActionEnum;
import com.company.commoneventlib.event.ProductEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.mockito.Mockito.*;

class StatisticServiceImplTest {

    private final StatisticRepository statisticRepository = mock(StatisticRepository.class);

    private final StatisticServiceImpl statisticService = new StatisticServiceImpl(statisticRepository);

    private ProductEvent productEvent;
    private Statistic previousStatistic;

    @BeforeEach
    void setUp() {
        productEvent = new ProductEvent();
        productEvent.setCurrentCategoryId(1L);
        productEvent.setPreviousCategoryId(1L);

        previousStatistic = new Statistic();
        previousStatistic.setTotalProducts(1L);
    }

    @ParameterizedTest
    @ValueSource(strings = { "CREATE_PRODUCT", "DELETE_PRODUCT" })
    void updateStatistics_ok_when_action_is_create_product_or_delete_product(ActionEnum action) throws CategoryNotFoundException {
        productEvent.setAction(action);

        when(statisticRepository.findByCategoryId(productEvent.getCurrentCategoryId())).thenReturn(Optional.of(previousStatistic));

        statisticService.updateStatistics(productEvent);
        verify(statisticRepository).save(any(Statistic.class));
    }

    @Test
    void updateStatistics_ok_when_action_is_change_category() throws CategoryNotFoundException {
        productEvent.setCurrentCategoryId(2L);
        productEvent.setAction(ActionEnum.CHANGE_CATEGORY);

        when(statisticRepository.findByCategoryId(productEvent.getPreviousCategoryId())).thenReturn(Optional.of(previousStatistic));

        statisticService.updateStatistics(productEvent);
        verify(statisticRepository, times(2)).save(any(Statistic.class));
    }
}
