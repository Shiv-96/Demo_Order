package com.order.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<OrderItem> items;

    private Float totalAmount;

    private Status status;

    @NotBlank(message = "Customer name can't be blank")
    @NotNull(message = "Customer name can't be null")
    private String customerName;

    @NotBlank(message = "Customer phone number can't be blank")
    @NotNull(message = "Customer phone number can't be null")
    private String customerPhone;

    private LocalDateTime createdAt;

}
