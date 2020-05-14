package com.service.impl;

import com.dao.FeedbackDao;
import com.domain.Feedback;
import com.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public int submitFeedback(Feedback feedback) {
        synchronized (this) {
            while (true) {
                String feedbackId = UUID.randomUUID().toString();
                Feedback one = feedbackDao.findOneById(feedbackId);
                if (one == null) {
                    feedback.setFeedbacksId(feedbackId);
                    return feedbackDao.createFeedback(feedback);
                }
            }
        }
    }

    @Override
    public List<Feedback> findFeedbackByIdAndStatus(String goodsId,int status){
        return feedbackDao.findAllByGidAndStatus(goodsId, status);
    }
}
