import React, { useState } from "react";
import Header from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import AddProductForm from "../../components/product/AddProductForm";
import Category from "../../components/category/Category";
import ManageProduct from "../../components/product/ManageProduct";
import "./css/addproduct.css";

const Admin = () => {
  const [currentScreen, setCurrentScreen] = useState("addProduct");

  const handleMenuClick = (screen) => {
    setCurrentScreen(screen);
  };

  return (
    <>
      <Header />
      <div className="ad-category-container">
        <div className="ad-category">
          <Category handleMenuClick={handleMenuClick} />
        </div>
        <div className="ad-add-product">
          {currentScreen === "addProduct" && <AddProductForm />}
          {currentScreen === "productManagement" && <ManageProduct />}
        </div>
      </div>

      <Footer />
    </>
  );
};

export default Admin;
