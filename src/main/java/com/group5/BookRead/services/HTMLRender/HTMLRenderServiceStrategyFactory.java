package com.group5.BookRead.services.HTMLRender;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HTMLRenderServiceStrategyFactory {
    private Map<StrategyName, HTMLRenderService> strategies;

    @Autowired
    public HTMLRenderServiceStrategyFactory(
            final Set<HTMLRenderService> strategySet) {
        createStrategy(strategySet);
    }

    /**
     * @return
     */
    public HTMLRenderService findStrategy(
            final StrategyName strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(final Set<HTMLRenderService> strategySet) {
        strategies = new HashMap<StrategyName, HTMLRenderService>();
        strategySet.forEach(
            strategy -> strategies.put(strategy.getStrategyName(), strategy)
        );
    }
}
