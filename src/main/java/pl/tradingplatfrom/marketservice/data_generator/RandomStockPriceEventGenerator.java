package pl.tradingplatfrom.marketservice.data_generator;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.tradingplatform.events.StockPriceEvent;
import pl.tradingplatfrom.marketservice.configuration.MarketConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RandomStockPriceEventGenerator {

    private static final Map<String, Double> priceCache = new ConcurrentHashMap<>();
    private static final Random random = new Random();
    private final MarketConfig marketConfig;

    @PostConstruct
    public void init() {
        marketConfig.getTickers().forEach(ticker -> priceCache.put(ticker, 100.0));
    }

    public StockPriceEvent createRandomEvent(String ticker) {
        Double currentPrice = priceCache.get(ticker);
        Double newPrice = calculateNewPrice(currentPrice);
        priceCache.put(ticker, newPrice);
        return new StockPriceEvent(ticker, "%s Inc.".formatted(ticker), newPrice, currentPrice, "USD", LocalDateTime.now(), UUID.randomUUID().toString());
    }

    private double calculateNewPrice(Double currentPrice) {
        if (currentPrice == null) currentPrice = random.nextDouble(100.0);
        double changePercent = (random.nextDouble() * 2 - 1) * 0.02;
        return currentPrice * (1 + changePercent);
    }

}
