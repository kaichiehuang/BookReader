package com.group5.BookRead.services.HTMLRender;

import com.group5.BookRead.services.book.DecoratorChainException;

import org.springframework.ui.Model;

public interface HTMLRenderService {
    String renderPage(Model model) throws DecoratorChainException;
    StrategyName getStrategyName();
}
