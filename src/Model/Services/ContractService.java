package Model.Services;

import java.util.Calendar;
import java.util.Date;

import Model.Entities.Contract;
import Model.Entities.Installment;

public class ContractService {
	
	private ServicePayment servicePayment;
	
	public ContractService(ServicePayment servicePayment) {
		this.servicePayment = servicePayment;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue()/months;
		for(int i = 1;i<=months;i++) {
			double updatedQuota = basicQuota + servicePayment.interest(basicQuota, i);
			double fullQuota = updatedQuota + servicePayment.paymentFee(updatedQuota);
			Date dueDate = addMonths(contract.getDate(), i);
			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}

	private Date addMonths(Date date, int N) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MONTH, N);
		return cal.getTime();
	}
}
