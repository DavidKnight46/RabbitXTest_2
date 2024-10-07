package org.rabbitx.rabbitbetest.controller;

import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.service.orderbook.OrderBookI;
import org.rabbitx.rabbitbetest.service.orderbook.OrderBookImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderBook")
public class OrderBookController {

    private final OrderBookI orderBook;

    public OrderBookController(OrderBookImpl orderBook) {
        this.orderBook = orderBook;
    }

    @PostMapping("/placeAPosition")
    @ResponseStatus(HttpStatus.OK)
    public void processATrade(@RequestParam String user,
                              @RequestParam String wallet,
                              @RequestBody NewTrade incomingTrade){
        orderBook.processAnOrder(user, wallet, incomingTrade);
    }

    @GetMapping("/getOrderBook")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionEntity> getOrderBook(@RequestParam String user){
        return orderBook.getAllPositions(user);
    }
}
