package book.store.service.impl;

import book.store.payload.request.OrderDetailRq;
import book.store.payload.request.UOrderRq;
import book.store.service.IOrderDetailService;
import book.store.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Override
    public boolean addOrderDetail(OrderDetailRq orderDetailRq) {
        return false;
    }
}
