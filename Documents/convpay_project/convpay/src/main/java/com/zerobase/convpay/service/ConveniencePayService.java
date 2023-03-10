package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {  // 편결이
    // private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    // private final CardAdapter cardAdapter = new CardAdapter();
    // public final DiscountInterface discountInterface = new DiscountByPayMethod();
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet, DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );
        this.discountInterface = discountInterface;
    }

    public PayResponse pay(PayRequest payRequest) {
        // MoneyUseResult moneyUseResult = moneyAdapter.use(payRequest.getPayAmount());

        // fail fast

        // Method()

        // Exception case5
        // Exception case4
        // Exception case1
        // Exception case2
        // Exception case3
        // 예외처리 & 유지보수가 쉽다.

        // ...
        // Success Case (Only one)

        // *Worst Example*
        /*
        CardUseResult cardUseResult;
        MoneyUseResult moneyUseResult;

        if(payRequest.getPayMethodType() == PayMethodType.CARD) {
            cardAdapter.authorization();
            cardAdapter.approval();
            cardUseResult = cardAdapter.capture(payRequest.getPayAmount());
        } else {
            moneyUseResult = moneyAdapter.use(payRequest.getPayAmount());
        }
        */

        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        /* if(payRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else {
            paymentInterface = moneyAdapter;
        } */

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult payment = paymentInterface.payment(discountedAmount);

        if(payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        // Success Case
        return new PayResponse(PayResult.SUCCESS, discountedAmount);
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        /* if(payCancelRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else {
            paymentInterface = moneyAdapter;
        } */

        CancelPaymentResult cancelPaymentResult =  paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if(cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }

        // Success Case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }
}
