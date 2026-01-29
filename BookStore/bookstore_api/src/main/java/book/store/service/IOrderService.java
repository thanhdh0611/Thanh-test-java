package book.store.service;

import book.store.entity.OrderDetail;
import book.store.payload.request.UOrderRq;

import java.util.List;

public interface IOrderService {


    boolean addOrder(UOrderRq uOrderRq,int id);

}
