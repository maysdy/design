package com.controller;

import com.domain.Feedback;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FeedbackService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
     private UserService userService;

    /**
     * 提交反馈
     * @param feedback
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/submitFeedback.do",method = {RequestMethod.POST})
    public void submitFeedback(@RequestBody Feedback feedback, HttpServletResponse response) throws IOException {
        int count = feedbackService.submitFeedback(feedback);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 获取所有评论信息
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getFeedbacks.do",method = {RequestMethod.GET})
    public @ResponseBody List<Feedback> getFeedback(@RequestParam String goodsId){
        List<Feedback> feedbacks = feedbackService.findFeedbackByIdAndStatus(goodsId, 0);
        for (Feedback feedback:feedbacks) {
            if (feedback.getUserId() != null){
                String name = userService.findName(feedback.getUserId());
                if (name != null){
                    feedback.setUserId(name);
                }
            }
        }
        return feedbacks;
    }
}
