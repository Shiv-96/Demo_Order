package com.order.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.Exception.MenuItemException;
import com.order.Exception.OrderException;
import com.order.Model.MenuItem;
import com.order.Model.Order;
import com.order.Model.OrderItem;
import com.order.Model.Status;
import com.order.Repository.MenuItemRepository;
import com.order.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public MenuItem addMenuItem(MenuItem menuItem) throws MenuItemException {
        // TODO Auto-generated method stub

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        if(savedMenuItem != null) {
            return savedMenuItem;
        }
        else{
            throw new MenuItemException("Menu Item is not added yet, Please try again");
        }

    }

    @Override
    public List<MenuItem> retrieveMenuItems() throws MenuItemException {
        // TODO Auto-generated method stub

        List<MenuItem> menuItems = menuItemRepository.findAll();

        if(menuItems.isEmpty()) {
            throw new MenuItemException("Currently no Item is present");
        }
        else{
            return menuItems;
        }
    }

    @Override
    public Order retrievOrdersbyId(Long orderId) throws OrderException {
        // TODO Auto-generated method stub

        Optional<Order> existingOrder = orderRepository.findById(orderId);

        if(existingOrder.isPresent()) {
            return existingOrder.get();
        }
        else {
            throw new OrderException("This order is not present");
        }

    }

    @Override
    public Order createNewOrder(OrderItem orderItem, String name, String phone)
            throws OrderException, MenuItemException {
        // TODO Auto-generated method stub

        MenuItem menuItem = orderItem.getMenuItem();

        if(menuItem == null) {
            throw new MenuItemException("There is no menu item present");
        }
        Order existingOrder = new Order();
        List<MenuItem> menuItems = menuItemRepository.findAll();
        boolean flag = false;
        for (MenuItem existingItem : menuItems) {
            if(existingItem.getName().equals(menuItem.getName()) && existingItem.getAvailableQuantity() > orderItem.getQuantity()){
                flag = true;
                orderItem.setSubTotal(existingItem.getPrice());
                Integer quantity = existingItem.getAvailableQuantity();
                existingItem.setAvailableQuantity(quantity - orderItem.getQuantity());
                Order createOrder = new Order();
                createOrder.setCreatedAt(LocalDateTime.now());
                createOrder.getItems().add(orderItem);
                createOrder.setStatus(Status.RECIEVED);
                createOrder.setTotalAmount(orderItem.getSubTotal());
                createOrder.setCustomerName(name);
                createOrder.setCustomerPhone(phone);
                existingOrder = orderRepository.save(createOrder);
            }
        }
        if (flag) {
            return existingOrder;
        }
        else {
            throw new OrderException("The Item you have order is not present");
        }
    }

}
