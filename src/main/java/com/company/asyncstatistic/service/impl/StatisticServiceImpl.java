package com.company.asyncstatistic.service.impl;

import com.company.asyncstatistic.entity.Category;
import com.company.asyncstatistic.entity.Statistic;
import com.company.asyncstatistic.exception.CategoryNotFoundException;
import com.company.asyncstatistic.repository.StatisticRepository;
import com.company.asyncstatistic.service.StatisticService;
import com.company.commoneventlib.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    @Override
    public void updateStatistics(ProductEvent productEvent) throws CategoryNotFoundException {
        Statistic statistic = statisticRepository.findByCategoryId(productEvent.getCurrentCategoryId())
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setId(productEvent.getCurrentCategoryId());

                    Statistic newStatistic = new Statistic();
                    newStatistic.setTotalProducts(0L);
                    newStatistic.setCategory(category);
                    return newStatistic;
                });

        switch (productEvent.getAction()) {
            case CREATE_PRODUCT:
                statistic.incrementTotalProducts();
                statisticRepository.save(statistic);
                break;
            case CHANGE_CATEGORY:
                Statistic previusCategoryStatistic = statisticRepository.findByCategoryId(productEvent.getPreviousCategoryId()).orElseThrow(CategoryNotFoundException::new);
                previusCategoryStatistic.decrementTotalProducts();
                statisticRepository.save(previusCategoryStatistic);

                statistic.incrementTotalProducts();
                statisticRepository.save(statistic);
                break;
            case DELETE_PRODUCT:
                statistic.decrementTotalProducts();
                statisticRepository.save(statistic);
                break;
            default:
                break;
        }
        log.info("Statistic updated");
    }
}
