import React from "react";
import Header from "../../components/header/Header";
import Banner from "../../components/banner/Banner"
import Content from "../../components/content/Content"
import Footer from "../../components/footer/Footer";
import Header1 from "../../components/header1/Header1";
import './css/home.css'

const Home = () => {
  return (
    <>
      <Header />
      {/* <Header1 /> */}
      <div className="slide-carousel">
      <Banner />
      </div>
      <Content />
      <Footer />
    </>
  );
};

export default Home;
