package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;

public class ConveniencePayService {  // 편결이
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();

    public PayResponse pay(PayRequest payRequest) {
        MoneyUseResult moneyUseResult = moneyAdapter.use(payRequest.getPayAmount());

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

        if(moneyUseResult == MoneyUseResult.USE_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        // Success Case
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());
    }

    /* public PayCancelResponse payConcel(PayCancelRequest payCancelRequest) {


    } */
}
