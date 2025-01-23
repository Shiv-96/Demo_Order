package com.order.Service;

import java.util.List;

import com.order.Exception.MenuItemException;
import com.order.Exception.OrderException;
import com.order.Model.MenuItem;
import com.order.Model.Order;
import com.order.Model.OrderItem;

public interface OrderService {

    public MenuItem addMenuItem(MenuItem menuItem) throws MenuItemException;

    public List<MenuItem> retrieveMenuItems() throws MenuItemException;

    public Order createNewOrder(OrderItem orderItem, String name, String phone) throws OrderException, MenuItemException;

    public Order retrievOrdersbyId(Long orderId) throws OrderException;

}
