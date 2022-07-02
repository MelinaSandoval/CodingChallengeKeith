import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World");
        Map<String, Map<String, Integer>> test = new HashMap<>();
        test.put("tran1", Map.<String, Integer>of(
                "user_id", 1,
                "ad_delivery_pennies", 1000,
                "transaction_timestamp", 1500000002
        ));
        test.put("tran2", Map.<String, Integer>of(
                "user_id", 1,
                "ad_delivery_pennies", 5000,
                "transaction_timestamp", 1500000001
        ));
        test.put("tran3", Map.<String, Integer>of(
                "user_id", 1,
                "payment_pennies", 500,
                "transaction_timestamp", 1500000003
        ));
        test.put("tran4", Map.<String, Integer>of(
                "user_id", 1,
                "ad_delivery_pennies", 1000,
                "transaction_timestamp", 1500000004,
                "payment_pennies", 500
        ));
        test.put("tran5", Map.<String, Integer>of(
                "user_id", 2,
                "ad_delivery_pennies", 1000,
                "transaction_timestamp", 1500000005,
                "payment_pennies", 500
        ));
        test.put("tran6", Map.<String, Integer>of(
                "user_id", 2,
                "payment_pennies", 500,
                "transaction_timestamp", 1500000005
        ));
        test.put("tran6", Map.<String, Integer>of(
                "user_id", 3,
                "payment_pennies", 1000,
                "transaction_timestamp", 1500000005
        ));

        List<Map<String, Integer>> billingStatuses = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : test.entrySet()) {
            billingStatuses.add(entry.getValue());
        }


        File writingFile = new File("src/main/resources/JacksonBillingStatus.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, billingStatuses);
        BillingStatus[] billingStatusesNew = objectMapper.readValue(writingFile, BillingStatus[].class);
        Set<BillingStatus> billingStatusSet = new HashSet<>();
        Set<Integer> userIDs = new HashSet<>();
        for (BillingStatus billingStatus : billingStatusesNew) {
            if (userIDs.contains(billingStatus.getUser_id())) {

            } else {
                userIDs.add(billingStatus.getUser_id());
                int ad_delivery_pennies = Arrays.stream(billingStatusesNew).filter(billingStatus1 -> billingStatus1.getUser_id() == billingStatus.getUser_id()).collect(Collectors.toList()).stream().mapToInt(BillingStatus::getAd_delivery_pennies).sum();
                int payment_pennies = Arrays.stream(billingStatusesNew).filter(billingStatus1 -> billingStatus1.getUser_id() == billingStatus.getUser_id()).collect(Collectors.toList()).stream().mapToInt(BillingStatus::getPayment_pennies).sum();
                billingStatusSet.add(new BillingStatus(billingStatus.getUser_id(), ad_delivery_pennies, payment_pennies));
            }
        }
        System.out.println(billingStatusSet);
    }

}