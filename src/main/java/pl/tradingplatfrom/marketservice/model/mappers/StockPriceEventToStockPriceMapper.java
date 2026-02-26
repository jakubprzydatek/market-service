package pl.tradingplatfrom.marketservice.model.mappers;

import org.springframework.stereotype.Component;
import pl.tradingplatform.events.StockPriceEvent;
import pl.tradingplatfrom.marketservice.model.StockPrice;

@Component
public class StockPriceEventToStockPriceMapper {

    public StockPrice mapTo(StockPriceEvent event) {
        return StockPrice.builder()
                .ticker(event.getTicker())
                .price(event.getCurrentPrice())
                .updatedAt(event.getTimestamp())
                .build();
    }

}
