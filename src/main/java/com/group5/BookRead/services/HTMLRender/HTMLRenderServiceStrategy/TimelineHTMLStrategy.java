package com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategy;

import java.util.List;

import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.StrategyName;
import com.group5.BookRead.services.timeline.ResponseTimeline;
import com.group5.BookRead.services.timeline.TimelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class TimelineHTMLStrategy implements HTMLRenderService {

    @Autowired
    TimelineService timelineService;
    
    /**
     *  get all activities to post on timeline
     * @param model
     * @param response
     * @return
     */
    @Override
    public String renderPage(final Model model) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            List<ResponseTimeline> timelines = timelineService
                    .getTimelines(userId);
            System.out.println("currentUser: " + userId + " timeline page:");
            for (ResponseTimeline t : timelines) {
                System.out.println(t);
            }
            model.addAttribute("timelines", timelines);
            return "timeline";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "{\"msg\":\"failure\"}";
        }
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.TimelineHTMLStrategy;
    }

}
