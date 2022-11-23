package com.tcs.refillmicroservice.model;

import javax.persistence.Entity;

@Entity
public class RefillOrderLineItem {
    private int subscription_id;
    private int refill_order_id;
    
}

/*
<For Every Subscription ID, and 
Refill Order ID, 
holds the list of drugs and 
quantity considered for Refill .>
*/