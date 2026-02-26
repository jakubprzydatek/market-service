package pl.tradingplatfrom.marketservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tradingplatform.events.StockPriceEvent;
import pl.tradingplatfrom.marketservice.model.StockPrice;
import pl.tradingplatfrom.marketservice.model.mappers.StockPriceEventToStockPriceMapper;
import pl.tradingplatfrom.marketservice.repositories.StockPriceRepository;

@Service
@RequiredArgsConstructor
public class StockPriceService {

    private final StockPriceRepository stockPriceRepository;
    private final StockPriceEventToStockPriceMapper stockPriceEventToStockPriceMapper;

    public StockPrice saveEvent(StockPriceEvent event) {
        StockPrice stockPrice = stockPriceEventToStockPriceMapper.mapTo(event);
        return stockPriceRepository.save(stockPrice);
    }

}
