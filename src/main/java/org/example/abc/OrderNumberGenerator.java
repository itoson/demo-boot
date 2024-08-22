package org.example.abc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrderNumberGenerator {
    private Map<LocalDate, OrderCounter> orderCounters = new HashMap<>();

    public synchronized String generateOrderNumber() {
        LocalDate today = LocalDate.now();
        OrderCounter counter = orderCounters.getOrDefault(today, new OrderCounter());
        String orderNumber = counter.nextOrderNumber();
        orderCounters.put(today, counter);
        return orderNumber;
    }

    private static class OrderCounter {
        private static final int MAX_NUMBER = 999;
        private char currentPrefix = 'A';
        private int currentNumber = 1;

        public String nextOrderNumber() {
            if (currentNumber > MAX_NUMBER) {
                currentNumber = 1;
                currentPrefix++;
            }
            if (currentPrefix > 'Z') {
                throw new RuntimeException("Exceeded order number limits for the day");
            }
            return String.format("%c%03d", currentPrefix, currentNumber++);
        }
    }

    public static void main(String[] args) {
        OrderNumberGenerator generator = new OrderNumberGenerator();
        for (int i = 0; i < 1050; i++) {
            System.out.println(generator.generateOrderNumber());
        }
    }
}
