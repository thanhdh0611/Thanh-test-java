import React, { useEffect, useState } from "react";
import './css/style.css'
import hero_1 from '../../assets/hero_1.jpg'
import hero_3 from '../../assets/hero_3.jpg'
import { FaShoppingCart, FaUser } from 'react-icons/fa';

const Header1 = () => {

  const [currentImage, setCurrentImage] = useState(0);
  const images = [hero_1, hero_3,hero_1]; // Thêm các ảnh slideshow khác vào đây

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImage((prevImage) => (prevImage + 1) % images.length);
    }, 2000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <header className="site-navbar mt-3">
        <div className="container-fluid">
          <div className="align-items-center header-wrapper">
            <div className="site-logo"><a href="index.html">Brand</a></div>
            <nav className="site-nav">
              <ul className="site-menu">
                <li><a href="index.html" className="nav-link active">Home</a></li>
                <li><a href="about.html">About</a></li>
                <li className="site-menu-item has-children dropdown">
                  <span className="nav-link">Job Listings</span>
                  <ul className="dropdown">
                    <li><a href="job-single.html">Job Single</a></li>
                    <li><a href="post-job.html">Post a Job</a></li>
                  </ul>
                </li>
                <li className="site-menu-item has-children dropdown">
                  <span className="nav-link">Pages</span>
                  <ul>
                    <li><a href="services.html">Services</a></li>
                    <li><a href="service-single.html">Service Single</a></li>
                    <li><a href="blog-single.html">Blog Single</a></li>
                    <li><a href="portfolio.html">Portfolio</a></li>
                    <li><a href="portfolio-single.html">Portfolio Single</a></li>
                    <li><a href="testimonials.html">Testimonials</a></li>
                    <li><a href="faq.html">Frequently Ask Questions</a></li>
                    <li><a href="gallery.html">Gallery</a></li>
                  </ul>
                </li>
                <li><a href="blog.html">Blog</a></li>
                <li><a href="contact.html">Contact</a></li>
                <li className="site-menu-item d-lg-none"><a href="post-job.html"><span className="mr-2">+</span> Post a Job</a></li>
                <li className="site-menu-item d-lg-none"><a href="login.html">Log In</a></li>
              </ul>
            </nav>

            <div className="header-actions">
              <div className="cart">
                <FaShoppingCart className="icon" />
                <a href="/cart">Cart</a>
              </div>
              <div className="login">
                <FaUser className="icon" />
                <a href="/login">Login</a>
              </div>

            </div>

          </div>
        </div>
      </header>

      <div className="hero" style={{ backgroundImage: `url(${images[currentImage]})` }}></div>

    </div>
  );
};

export default Header1;
