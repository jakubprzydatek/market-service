package pl.tradingplatfrom.marketservice.producers;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.tradingplatform.events.StockPriceEvent;
import pl.tradingplatfrom.marketservice.configuration.MarketConfig;
import pl.tradingplatfrom.marketservice.data_generator.RandomStockPriceEventGenerator;
import pl.tradingplatfrom.marketservice.services.StockPriceService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
public class MarketDataProducer {

    private final KafkaTemplate<String, StockPriceEvent> kafkaTemplate;
    private final StockPriceService stockPriceService;
    private final MarketConfig marketConfig;
    private final RandomStockPriceEventGenerator randomStockPriceEventGenerator;

    @Scheduled(fixedRate = 5000)
    public void sendRandomPrice() {
        marketConfig.getTickers().forEach(ticker -> {
            StockPriceEvent event = randomStockPriceEventGenerator.createRandomEvent(ticker);
            kafkaTemplate.send("market-prices", event.getTicker(), event);
            stockPriceService.saveEvent(event);
        });
    }

}
