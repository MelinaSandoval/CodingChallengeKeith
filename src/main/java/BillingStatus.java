import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"transaction_timestamp"})
public class BillingStatus {
    private Integer user_id;
    private int ad_delivery_pennies;
    private int payment_pennies;
    private long transaction_timestamp;

    public BillingStatus() {
    }

    public BillingStatus(Integer user_id, int ad_delivery_pennies, int payment_pennies) {
        this.user_id = user_id;
        this.ad_delivery_pennies = ad_delivery_pennies;
        this.payment_pennies = payment_pennies;
    }

    public Integer getUser_id() {
        return user_id;
    }

    @JsonProperty("user_id")
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getAd_delivery_pennies() {
        return ad_delivery_pennies;
    }

    @JsonProperty("ad_delivery_pennies")
    public void setAd_delivery_pennies(int ad_delivery_pennies) {
        this.ad_delivery_pennies = ad_delivery_pennies;
    }

    public int getPayment_pennies() {
        return payment_pennies;
    }

    @JsonProperty("payment_pennies")
    public void setPayment_pennies(int payment_pennies) {
        this.payment_pennies = payment_pennies;
    }


    public long getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(long transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }

    @Override
    public String toString() {
        return user_id +
                "= ad_delivery_pennies:" + ad_delivery_pennies +
                " payment_pennies:" + payment_pennies;
    }
}
