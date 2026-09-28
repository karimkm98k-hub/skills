package com.training.skills.Services;

import com.training.skills.Entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private EmailService emailService;

    @Transactional(rollbackFor = InsufficientStockException.class)
    public void placeOrder(OrderRequest request) throws Exception {
        Order order = new Order();
        order.setCustomerEmail(request.getEmail());
        orderRepository.save(order);

        inventoryService.reduceStock(request.getItems()); // throws InsufficientStockException (checked)

        emailService.sendConfirmation(order.getCustomerEmail());
    }

    public void processBatch(List<OrderRequest> requests) throws Exception {
        for (OrderRequest r : requests) {
            placeOrder(r);
        }
    }

    public List<OrderSummaryDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(o -> new OrderSummaryDto(o.getId(), o.getItems().size()))
                .toList();
    }
}
