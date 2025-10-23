package com.dhruv.aiservice.service;

import com.dhruv.aiservice.entity.Activity;
import com.dhruv.aiservice.entity.Recommendations;
import com.dhruv.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group", containerFactory = "kafkaListenerContainerFactory")
    public void processActivity(@Payload Activity activity){

        log.info("Received Activity for processing: userId={} ", activity.getUserId());
         Recommendations recommendations =  activityAiService.generateRecommendation(activity);
         recommendationRepository.save(recommendations);
    }
}
