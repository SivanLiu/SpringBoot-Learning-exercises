package com.spring.bug.goods.controller;

import com.spring.bug.goods.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService = null;

    @GetMapping("/test")
    public ModelAndView testPage() {
        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }

    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";
        Result result = new Result(success, message);
        return result;
    }

    class Result {
        private boolean success = false;
        private String message = null;

        public Result() {

        }

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Result{" + "success=" + success + ", message='" + message + '\'' + '}';
        }
    }
}
