package com.movie.booking.enums;

public enum PaymentTypes {
	UPI("upi"),
    ONLINE("online"),
    CREDITCARD("creditcard");
	
	private final String value;

	
	PaymentTypes(String value) {
		 this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    
    public static String checkPaymentType(String paymentType) {
        for (PaymentTypes b : PaymentTypes.values()) {
            if (b.value.equalsIgnoreCase(paymentType)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + paymentType + " found");
    }
    
}
