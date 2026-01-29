import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { FiChevronLeft, FiChevronRight } from "react-icons/fi";
import "./banner.css";

const Banner = () => {
  const images = [
    "https://preview.colorlib.com/theme/abcbook/assets/img/hero/h1_hero2.jpg.webp",
    "https://preview.colorlib.com/theme/abcbook/assets/img/hero/h1_hero3.jpg.webp",
    "https://preview.colorlib.com/theme/abcbook/assets/img/hero/h1_hero1.jpg.webp",
  ]; // Đường dẫn đến ảnh phong cảnh

  const [currentSlide, setCurrentSlide] = useState(0);

  const goToPreviousSlide = () => {
    setCurrentSlide((prevSlide) =>
      prevSlide === 0 ? images.length - 1 : prevSlide - 1
    );
  };

  const goToNextSlide = () => {
    setCurrentSlide((prevSlide) =>
      prevSlide === images.length - 1 ? 0 : prevSlide + 1
    );
  };

  return (
    <section className="banner-section container">
      <div className="carousel-container custom-height">
        <Carousel
          autoPlay
          interval={2000}
          infiniteLoop
          showThumbs={false}
          selectedItem={currentSlide}
          renderArrowPrev={(onClickHandler, hasPrev, label) =>
            hasPrev && (
              <button
                type="button"
                className="carousel-arrow carousel-arrow-left"
                onClick={onClickHandler}
              >
                <FiChevronLeft />
              </button>
            )
          }
          renderArrowNext={(onClickHandler, hasNext, label) =>
            hasNext && (
              <button
                type="button"
                className="carousel-arrow carousel-arrow-right"
                onClick={onClickHandler}
              >
                <FiChevronRight />
              </button>
            )
          }
          onChange={(index) => setCurrentSlide(index)}
        >
          {images.map((imageUrl, index) => (
            <div key={index} className="slide">
              <img src={imageUrl} alt={`Banner ${index + 1}`} />
            </div>
          ))}
        </Carousel>
        <div className="carousel-navigation">
          <button
            type="button"
            className="carousel-arrow carousel-arrow-left"
            onClick={goToPreviousSlide}
          >
            <FiChevronLeft />
          </button>
          <button
            type="button"
            className="carousel-arrow carousel-arrow-right"
            onClick={goToNextSlide}
          >
            <FiChevronRight />
          </button>
        </div>
      </div>
    </section>
  );
};

export default Banner;
