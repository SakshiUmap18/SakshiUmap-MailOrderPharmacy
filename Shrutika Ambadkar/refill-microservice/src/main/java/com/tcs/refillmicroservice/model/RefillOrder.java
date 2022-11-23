package com.tcs.refillmicroservice.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class RefillOrder {
	private int subscription_id;
	private Date refill_date;
	private int refill_order_id;
	private int refill_quantity;
	private String refill_status;
}

/*
 * <For Every Subscription ID, holds the Refill Details like Refill Date, Refill
 * Order ID, Quantity Status, etc.>
 */