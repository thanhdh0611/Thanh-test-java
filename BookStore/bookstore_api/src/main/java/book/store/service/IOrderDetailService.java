package book.store.service;


import book.store.payload.request.OrderDetailRq;

public interface IOrderDetailService {


    boolean addOrderDetail(OrderDetailRq orderDetailRq);
}
