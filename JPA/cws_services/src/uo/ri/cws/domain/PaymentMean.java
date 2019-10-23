package uo.ri.cws.domain;

import alb.util.assertion.Argument;

public abstract class PaymentMean {
	private double accumulated = 0.0;

	/*
	 * Relacion pays
	 */
	private Client client;

	public Client getClient() {
		return client;
	}

	void _setClient(Client client) {
		this.client = client;
	}

	/*
	 * Relacion pays
	 */
	private CreditCard creditCard;

	public CreditCard getCreditCard() {
		return creditCard;
	}

	void _setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	/*
	 * Relacion pays
	 */
	private Cash cash;

	public Cash getCash() {
		return cash;
	}

	void _setCash(Cash cash) {
		this.cash = cash;
	}

	/*
	 * Relacion pays
	 */
	private Voucher voucher;

	public Voucher getVoucher() {
		return voucher;
	}

	void _setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public PaymentMean(Client client, CreditCard creditCard, Cash cash, Voucher voucher) {
		super();
		Argument.isNotNull(client);
		Argument.isNotNull(creditCard);
		Argument.isNotNull(cash);
		Argument.isNotNull(voucher);
		this.client = client;
		this.creditCard = creditCard;
		this.cash = cash;
		this.voucher = voucher;
	}

	public void pay(double importe) {
		this.accumulated += importe;
	}

}
