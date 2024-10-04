package org.rabbitx.rabbitbetest.controller;

import org.rabbitx.rabbitbetest.service.orderbook.OrderBookI;
import org.rabbitx.rabbitbetest.service.orderbook.OrderBookImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderBook")
public class OrderBookController {

    private final OrderBookI orderBook;

    public OrderBookController(OrderBookImpl orderBook) {
        this.orderBook = orderBook;
    }

    @PostMapping("/placeAPosition")
    @ResponseStatus(HttpStatus.OK)
    public void placeAPosition(@RequestParam String user,
                               @RequestParam String wallet){

    }
}
