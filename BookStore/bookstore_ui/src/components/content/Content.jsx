import React, { useState, useEffect  } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./content.css";
import StarRatingComponent from "react-star-rating-component";
import { AiOutlineFilter, AiOutlineSearch } from 'react-icons/ai';
import { getAllProducts } from './action_content';



const getTokenFromLocalStorage = () => {
  return localStorage.getItem("token");
};

const Content = () => {



  const [productList1, setProductList] = useState([]);

  useEffect(() => {
    const token = getTokenFromLocalStorage();
    // console.log(token)
    getAllProducts(token)
    .then((data) => setProductList(data))
    .catch((error) => {
      console.error("Error fetching data from API: ", error);
    });
  }, []);

  const productsPerPage = 12;
  const totalPages = Math.ceil(productList1.length / productsPerPage);

  const [currentPage, setCurrentPage] = useState(1);
  const startIndex = (currentPage - 1) * productsPerPage;
  const endIndex = startIndex + productsPerPage;
  const currentProducts = productList1.slice(startIndex, endIndex);

  const goToPage = (page) => {
    setCurrentPage(page);
  };

  return (
    <section className="container container-content">
      <div className="row category-container">
        <div className="col-12">
          <h2>Category</h2>
        </div>
        <div className="col-12 sup-filter-category">
          <div className="sup-category">
            <div>Love</div>
            <div>Bussiness</div>
            <div>Family</div>
            <div>Ebook</div>
          </div>
          <div className="filter-category">
            <div className=""><AiOutlineFilter />{'\t'} Filter</div>
            <div className=""><AiOutlineSearch />{'\t'}Search</div>
          </div>
        </div>
      </div>
      <div className="row product-container">
        <div className="col-12 product-container_text">
          <h2>Danh sách sản phẩm</h2>
        </div>
        <div className="col-12">
          <div className="product-list">
            <div className="row">
              {currentProducts.map((product) => (
                <div className="col-xl-2 col-lg-3 col-md-4 col-sm-6" key={product.id}>
                  <div className="product">
                    <Link to={`/product/${product.id}`}>
                      <div className="product-image">
                        <img
                          src={require(`../../assets/${product.image}`)}
                          alt={product.name}
                          className=""
                        />
                      </div>
                      <h4 className="product-name">{product.name}</h4>
                      <p className="author-name">{product.author}</p>
                    </Link>
                    <div className="product-price-rating">
                      <div className="product-rating">
                      <StarRatingComponent
                        name={`rating_${product.id}`}
                        value={product.rating}
                        starCount={5}
                        starColor="#ffd700"
                        emptyStarColor="#ddd"
                        editing={false}
                      />
                      <span className="rating-value">{product.rating}</span>
                      </div>
                      <div className="product_price">
                          <p className="product_price-text">${product.price}</p>
                      </div>
                      
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="col-12">
          <div className="pagination product-pagination">
            {Array.from({ length: totalPages }).map((_, index) => (
              <button
                key={index}
                className={`btn ${
                  currentPage === index + 1 ? "btn-primary" : "btn-secondary"
                }`}
                onClick={() => goToPage(index + 1)}
              >
                {index + 1}
              </button>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
};

export default Content;
