package com.order.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.Exception.MenuItemException;
import com.order.Exception.OrderException;
import com.order.Model.MenuItem;
import com.order.Model.Order;
import com.order.Model.OrderItem;
import com.order.Service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/menu-items")
    public ResponseEntity<MenuItem> addMenuItemHandler(@RequestBody MenuItem menuItem) throws MenuItemException {
        return new ResponseEntity<>(orderService.addMenuItem(menuItem), HttpStatus.CREATED);
    }

    @GetMapping("/menu-items")
    public ResponseEntity<List<MenuItem>> retrieveMenuItemsHandler() throws MenuItemException {
        return new ResponseEntity<>(orderService.retrieveMenuItems(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createNewOrderHandler(@RequestBody OrderItem orderItem, @RequestParam("name") String name, @RequestParam("phone") String phone) throws OrderException, MenuItemException {
        return new ResponseEntity<>(orderService.createNewOrder(orderItem, name, phone), HttpStatus.CREATED);
    }

    public ResponseEntity<Order> retrievOrdersbyIdHandler(@PathVariable("id") Long id) throws OrderException {
        return new ResponseEntity<>(orderService.retrievOrdersbyId(id), HttpStatus.OK);
    }

}
