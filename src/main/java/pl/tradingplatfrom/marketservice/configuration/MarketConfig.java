package pl.tradingplatfrom.marketservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "market.data")
@Getter
@Setter
public class MarketConfig {

    private List<String> tickers;

}
