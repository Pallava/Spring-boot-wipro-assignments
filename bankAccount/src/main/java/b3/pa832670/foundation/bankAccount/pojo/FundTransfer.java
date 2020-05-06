package b3.pa832670.foundation.bankAccount.pojo;

import java.math.BigDecimal;

public class FundTransfer {

	private Integer fromAccount;
	private Integer toAccount;
	private BigDecimal amountTotransfer;

	public FundTransfer() {

	}

	public FundTransfer(Integer fromAccount, Integer toAccount, BigDecimal amountTotransfer) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amountTotransfer = amountTotransfer;
	}

	public Integer getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Integer fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Integer getToAccount() {
		return toAccount;
	}

	public void setToAccount(Integer toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmountTotransfer() {
		return amountTotransfer;
	}

	public void setAmountTotransfer(BigDecimal amountTotransfer) {
		this.amountTotransfer = amountTotransfer;
	}

	@Override
	public String toString() {
		return "FundTransfer [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amountTotransfer="
				+ amountTotransfer + "]";
	}

}
