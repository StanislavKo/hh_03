package com.cv.jasonp01.entity;

import java.math.BigDecimal;

public class Account {

	private Long id;
	private String name;
	private String currency;
	private BigDecimal amount;

	public Account() {
		super();
	}

	public Account(Long id, String name, String currency, BigDecimal amount) {
		super();
		this.id = id;
		this.name = name;
		this.currency = currency;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public static class AccountBuilder {
		private Long id;
		private String name;
		private String currency;
		private BigDecimal amount;

		public AccountBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public AccountBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public AccountBuilder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public AccountBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public Account build() {
			return new Account(id, name, currency, amount);
		}
	}
}
