package Model.Services;

public interface ServicePayment {
	
	double paymentFee (double amount);
	double interest(double amount, int months);
	

}
