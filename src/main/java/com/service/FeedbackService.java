package com.service;

import com.domain.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * 提交反馈
     * @param feedback
     * @return
     */
    int submitFeedback(Feedback feedback);

    /**
     * 根据goodsId和status查询反馈
     * @return
     */
    List<Feedback> findFeedbackByIdAndStatus(String goodsId,int status);
}
