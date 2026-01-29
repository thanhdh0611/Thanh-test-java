import React from 'react';
import './footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="row">
          <div className="col-md-6">
            <h3>About Us</h3>
            <p>Dự án này chỉ là Demo dự án E-Commerce đầu tiên tự làm của mình (Leo Hoàng) và Huy là người đã giúp đỡ rất nhiều.. Dự án JAVA này dành cho các bạn mới và trái ngành có thể tham khảo thêm về Design Pattern cũng như cách thức hoạt động của SpringBoot,
               Vì là Fullstack nên từ liên kết BE qua FE đều có nên cũng phải biết cách liên kết.. cách hoạt động giữa Docker MySQL PostMan và đưa lên FrontEnd</p>
            <p></p>
          </div>
          <div className="col-md-3">
            <h3>Quick Links</h3>
            <ul>
              <li><a href="/">Home</a></li>
              <li><a href="/shop">Shop</a></li>
              <li><a href="/about">About</a></li>
              <li><a href="/contact">Contact</a></li>
            </ul>
          </div>
          <div className="col-md-3">
            <h3>Contact Info</h3>
            <ul>
              <li>2bis, Nguyễn Thị Minh Khai,Q1</li>
              <li>Phone: 0961525395</li>
              <li>Email: hoangvm95@gmail.com</li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;