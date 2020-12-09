package com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategy;

import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.StrategyName;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class LoginHTMLStrategy implements HTMLRenderService {
    @Override
    public String renderPage(final Model model) {
        return "login";
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.LoginHTMLStrategy;
    }
}
