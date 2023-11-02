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
        Double installmentAmount = (contract.getTotalValue() / months);

        for (int i = 0; i <= (months-1); i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i+1);

            Double simpleMonthInterest = onlinePaymentService.interest(installmentAmount, (i+1));

            Double paymentServiceFee = onlinePaymentService.paymentFee(simpleMonthInterest);

            contract.getInstallments().add(new Installment(dueDate, paymentServiceFee));
        }
    }

}
