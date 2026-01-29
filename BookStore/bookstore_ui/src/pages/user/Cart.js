import React, { useState, useEffect } from "react";
import Header from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import "./css/cart.css";
import { sendPaymentData } from "./js/action_cart";

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [cartData, setCartData] = useState({ order: [], totalPrice: 0 });

  useEffect(() => {
    const existingCartData = JSON.parse(localStorage.getItem("cartData")) || {
      order: [],
      totalPrice: 0,
    };
    setCartData(existingCartData);
  }, []);

  const increaseQuantity = (itemId) => {
    setCartData((prevData) => ({
      ...prevData,
      order: prevData.order.map((item) =>
        item.id === itemId ? { ...item, quantity: item.quantity + 1 } : item
      ),
    }));
  };

  const decreaseQuantity = (itemId) => {
    setCartData((prevData) => ({
      ...prevData,
      order: prevData.order.map((item) =>
        item.id === itemId && item.quantity > 1
          ? { ...item, quantity: item.quantity - 1 }
          : item
      ),
    }));
  };

  const totalPrice = cartData.order.reduce(
    (total, item) => total + item.quantity * item.price,
    0
  );

  const removeItem = (itemId) => {
    setCartData((prevData) => ({
      ...prevData,
      order: prevData.order.filter((item) => item.id !== itemId),
    }));
  };

  const handlePayment = () => {
    // Lưu dữ liệu vào local storage
    localStorage.setItem("cartData", JSON.stringify(cartData));

    // Gọi hàm để gửi dữ liệu thanh toán lên server
    sendPaymentData(cartData);
  };

  return (
    <div>
      <Header />
      <div className="container cart-container">
        <div className="cart-container_header">
          <a href="/">Home</a>
          <span>Giỏ hàng</span>
        </div>

        <div className="row cart-items">
          {cartData.order.map((item) => (
            <div key={item.id} className="col-md-4">
              <div className="card mb-3">
                <div className="d-flex align-items-start">
                  <img
                    src={require(`../../assets/${item.image}`)}
                    alt={item.name}
                    className="card-img-top larger-image"
                  />
                  <div className="card-body">
                    <h5 className="card-title">{item.name}</h5>
                    <p className="card-text">Số lượng: {item.quantity}</p>
                    <div className="btn-group">
                      <button
                        className="btn btn-primary"
                        onClick={() => increaseQuantity(item.id)}
                      >
                        +
                      </button>
                      <button
                        className="btn btn-primary"
                        onClick={() => decreaseQuantity(item.id)}
                      >
                        -
                      </button>
                    </div>
                    <button
                      className="btn btn-danger mt-2"
                      onClick={() => removeItem(item.id)}
                    >
                      Xóa
                    </button>
                    <p className="card-text mt-2">
                      Tổng giá tiền: {item.quantity * item.price}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
        <div className="cart-total">
          <button onClick={handlePayment}>Thanh toán</button>
          <p className="font-weight-bold">Tổng số tiền: {totalPrice}</p>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default CartPage;
