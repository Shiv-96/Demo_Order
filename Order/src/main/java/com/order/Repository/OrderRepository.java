package com.order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
