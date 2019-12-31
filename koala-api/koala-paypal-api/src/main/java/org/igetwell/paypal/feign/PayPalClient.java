package org.igetwell.paypal.feign;

import org.igetwell.paypal.dto.request.PayPalRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "koala-paypal")
public interface PayPalClient {

    @PostMapping("/paypal/paypal/aliPay")
    Map<String, String> aliPay(@RequestBody PayPalRequest payPalRequest);

    /**
     * 公众号支付，APP内调起微信支付
     * 微信H5、APP内调起支付
     * @return
     */
    @PostMapping("/paypal/paypal/wxPay")
    Map<String, String> wxPay(@RequestBody PayPalRequest payPalRequest);
}
