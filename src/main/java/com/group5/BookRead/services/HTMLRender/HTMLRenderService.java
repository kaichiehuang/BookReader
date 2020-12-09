package com.group5.BookRead.services.HTMLRender;

import com.group5.BookRead.services.book.DecoratorChainException;

import org.springframework.ui.Model;

public interface HTMLRenderService {
    String renderPage(final Model model) throws DecoratorChainException;
    StrategyName getStrategyName();
}
