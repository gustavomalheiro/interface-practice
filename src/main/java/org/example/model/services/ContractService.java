package org.example.model.services;

import org.example.model.entities.Contract;
import org.example.model.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        // aqui vai ter a lógica da taxa
        // e é aqui que eu vou instanciar as installments
        double installmentAmount = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double simpleMonthInterest = onlinePaymentService.interest(installmentAmount, i);
            double paymentServiceFee = onlinePaymentService.paymentFee(installmentAmount + simpleMonthInterest);
            double quota = installmentAmount + simpleMonthInterest + paymentServiceFee;

            contract.getInstallments().add(new Installment(dueDate, quota));
        }
    }

}
