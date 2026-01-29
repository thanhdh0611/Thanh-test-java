import React from "react";
import { FaShoppingCart, FaUser } from "react-icons/fa";
import "../header/header.css";
import logo from "../../assets/logo";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

const Header = () => {
  return (
    <header className="header">
      <div className="header-top container">
        <div className="d-flex justify-content-between align-items-center ">
          <div className="header-info-left d-flex align-items-center logo">
            <img src={logo} alt="Logo" />
            <form action="#" className="form-box">
              <div className="search-container">
                <input
                  type="text"
                  placeholder="Search book by author or publisher"
                  className="search-input"
                />
                <div className="search-icon">
                  <FontAwesomeIcon
                    icon={faSearch}
                    className="search-icon-svg"
                  />
                </div>
              </div>
            </form>
          </div>
        </div>

        <div className="d-flex flex-row gap-3">
          <div className="cart">
            <FaShoppingCart className="icon" />
            <a href="/cart">Cart</a>
          </div>
          <div className="login mr-3">
            <FaUser className="icon" />
            <a href="/login">Login</a>
          </div>
        </div>
      </div>

      <div className="header-bottom">
        <div className="container">
          <nav className="nav-menu">
            <ul className="navbar-row">
              <li className="nav-item">
                <a className="nav-link" href="/">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/shop">
                  Shop
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/shop">
                  Blog
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/about">
                  About
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;
