package com.company.asyncstatistic.event;

import com.company.asyncstatistic.exception.CategoryNotFoundException;
import com.company.asyncstatistic.service.StatisticService;
import com.company.commoneventlib.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudUpdateStatisticsStream {

    private final StatisticService statisticService;

    @KafkaListener(
            topics = "${spring.kafka.topic.cloud-update-statistics}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeDisburse(ProductEvent productEvent, Acknowledgment ack) throws CategoryNotFoundException {
        log.info("EventConsumer - productEvent");
        statisticService.updateStatistics(productEvent);
        ack.acknowledge();
    }
}
