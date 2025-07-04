package strategy.expiration;

public class NeverExpiresStrategy implements ExpirationStrategy {
    @Override
    public boolean isExpired() {
        return false;
    }
}