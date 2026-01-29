import React, { useState, useEffect } from "react";
import "./css/productdetail.css";
import { useParams } from "react-router-dom";
import { getBookDetail } from "./js/action_bookdetail";

const getTokenFromLocalStorage = () => {
  return localStorage.getItem("token");
};

const BookDetail = () => {
  const [quantity, setQuantity] = useState(1);
  const [bookDetail, setProductDetail] = useState(null);
  const [pricePerItem, setPricePerItem] = useState(0);
  const { id } = useParams();

  useEffect(() => {
    const token = getTokenFromLocalStorage(); // Lấy token từ localStorage

    getBookDetail(token, id)
      .then((data) => {
        setProductDetail(data); // Lưu thông tin chi tiết sản phẩm vào state
        setPricePerItemFromData(data);
      })
      .catch((error) => {
        console.error("Error fetching product data:", error);
      });
  }, []); // Chạy một lần khi component được tạo

  const setPricePerItemFromData = (data) => {
    if (data) {
      setPricePerItem(data.price); // Set pricePerItem từ dữ liệu chi tiết sách
    } else {
      setPricePerItem(0); // Nếu dữ liệu không có, set giá trị mặc định là 0
    }
  };

  const handleIncreaseQuantity = () => {
    setQuantity(quantity + 1);
  };

  const handleDecreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const handleAddToCart = () => {
    // Lấy danh sách sản phẩm từ local storage (nếu có)
    const existingCartData = JSON.parse(localStorage.getItem("cartData")) || {
      order: [],
      totalPrice: 0,
    };

    const existingCartItems = existingCartData.order;

    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
    const existingItemIndex = existingCartItems.findIndex(
      (item) => item.id === bookDetail.id
    );

    if (existingItemIndex !== -1) {
      // Nếu sản phẩm đã tồn tại, cập nhật lại số lượng và giá trị
      existingCartItems[existingItemIndex].quantity += quantity;
      existingCartItems[existingItemIndex].price = (
        parseFloat(existingCartItems[existingItemIndex].price) + totalPrice
      ).toFixed(2);
    } else {
      // Nếu sản phẩm chưa tồn tại, thêm sản phẩm mới vào danh sách
      const newItem = {
        id: bookDetail.id,
        name: bookDetail.name,
        image: bookDetail.image,
        quantity: quantity,
        price: totalPrice.toFixed(2),
      };
      existingCartItems.push(newItem);
    }

    // Cập nhật tổng giá tiền
    existingCartData.totalPrice = (
      parseFloat(existingCartData.totalPrice) + totalPrice
    ).toFixed(2);

    // Lưu dữ liệu vào local storage
    localStorage.setItem("cartData", JSON.stringify(existingCartData));
  };

  if (!bookDetail) {
    return <p>Loading...</p>;
  }

  const totalPrice = pricePerItem * quantity;

  return (
    <section className="container product-detail">
      <div className="product-image-info">
        <div className="product-detail-image">
          <img
            src={require(`../../assets/${bookDetail.image}`)}
            alt="Product"
          />
        </div>
        <div className="product-info">
          <h2 className="product-name">{bookDetail.name}</h2>
          {/* <p className="author">Author Name</p> */}

          <p className="price">Price: ${totalPrice.toFixed(2)}</p>
          <div className="quantity-control">
            <button className="decrease-btn" onClick={handleDecreaseQuantity}>
              -
            </button>
            <span className="quantity">{quantity}</span>
            <button className="increase-btn" onClick={handleIncreaseQuantity}>
              +
            </button>
          </div>
          <button className="add-to-cart-btn" onClick={handleAddToCart}>
            Add to Cart
          </button>
        </div>
      </div>
      <div className="product-description">
        <p className="product-sumary">Product Summary Description</p>
        <p className="">{bookDetail.desc}</p>
      </div>
    </section>
  );
};

export default BookDetail;
