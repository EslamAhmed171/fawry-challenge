package strategy.expiration;

import java.time.LocalDate;

public class DateExpirationStrategy implements ExpirationStrategy {
    private final LocalDate expirationDate;

    public DateExpirationStrategy(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}