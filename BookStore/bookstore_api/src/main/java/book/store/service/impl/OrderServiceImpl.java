package book.store.service.impl;

import book.store.entity.Book;
import book.store.entity.Order;
import book.store.entity.OrderDetail;
import book.store.entity.Users;
import book.store.entity.ids.OrderDetailsIds;
import book.store.payload.request.OrderDetailRq;
import book.store.payload.request.UOrderRq;
import book.store.repository.BookRepository;
import book.store.repository.OrderDetailRepository;
import book.store.repository.OrderRepository;
import book.store.repository.UserRepository;
import book.store.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public boolean addOrder(UOrderRq uOrderRq, int id) {
        Users users = userRepository.findById(id).get();

        Order order = new Order();
        order.setPrice(uOrderRq.getTotalPrice());
        order.setUser(users);
        orderRepository.save(order);

        List<OrderDetailRq> rqList = uOrderRq.getOrder();

        for (OrderDetailRq ord: rqList) {

            Book book = bookRepository.findById(ord.getId()).get();

            OrderDetailsIds orderDetailsIds = new OrderDetailsIds();
            orderDetailsIds.setBookId(ord.getId());
            orderDetailsIds.setOrderId(order.getId());


            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIds(orderDetailsIds);
            orderDetail.setBook(book);
            orderDetail.setOrder(order);
            orderDetail.setPrice(ord.getPrice());
            orderDetail.setQuantity(ord.getQuantity());
            orderDetailRepository.save(orderDetail);

        }
        System.out.println(uOrderRq);
        return false;
    }
}
