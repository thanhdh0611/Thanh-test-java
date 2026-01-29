import React, { useState } from "react";
import "./css/addproductform.css";
import Select from "react-select";

const options = [
  { value: "option1", label: "Option 1" },
  { value: "option2", label: "Option 2" },
  { value: "option3", label: "Option 3" },
  // Thêm các option tương ứng với danh sách category
];

const AddProductForm = () => {
  const [bookTitle, setName] = useState("");
  const [category, setCategory] = useState("");
  const [bookImage, setBookImage] = useState("");
  const [authorName, setAuthorName] = useState("");
  const [price, setPrice] = useState("");
  const [summary, setSummary] = useState("");
  const [isbn13, setIsbn13] = useState("");
  const [description, setDescription] = useState("");
  const [quantity, setQuantity] = useState("");

  const handleFormSubmit = (event) => {
    event.preventDefault();

    // TODO: Logic for adding product

    // Clear form fields after submission
    setName("");
    setBookImage("");
    setAuthorName("");
    setPrice("");
    setSummary("");
    setIsbn13("");
    setDescription("");
    setQuantity("");
    setCategory("");
  };

  const handleChange = (selectedOption) => {
    setCategory(selectedOption.value);
  };

  return (
    <div className="add-product-form">
      <h2>Add Product</h2>
      <form onSubmit={handleFormSubmit}>
        <div className="form-group">
          <label htmlFor="bookName">Book Name *</label>
          <input
            type="text"
            id="bookName"
            value={bookTitle}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="category">Category *</label>
          <Select
            id="category"
            value={options.find((option) => option.value === category)}
            onChange={handleChange}
            options={options}
          />
        </div>
        <div className="form-group">
          <label htmlFor="isbn13">Isbn13</label>
          <input
            type="text"
            id="isbn13"
            value={isbn13}
            onChange={(e) => setIsbn13(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="description">Description *</label>
          <input
            type="text"
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="bookImage">Book Image *</label>
          <input
            type="text"
            id="bookImage"
            value={bookImage}
            onChange={(e) => setBookImage(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="authorName">Author Name *</label>
          <input
            type="text"
            id="authorName"
            value={authorName}
            onChange={(e) => setAuthorName(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="price">Price *</label>
          <input
            type="text"
            id="price"
            pattern="^\d+(\.\d{1,2})?$" // Chỉ cho phép nhập số tiền
            value={price}
            onChange={(e) => {
              const inputValue = e.target.value;
              if (inputValue.match(/^\d+(\.\d{1,2})?$/)) {
                setPrice(inputValue);
              }
            }}
          />
        </div>

        <div className="form-group">
          <label htmlFor="quantity">Quantity *</label>
          <input
            type="text"
            id="quantity"
            pattern="[0-9]*" // Chỉ cho phép nhập số
            value={quantity}
            onChange={(e) => {
              const inputValue = e.target.value;
              if (inputValue.match(/^[0-9]*$/)) {
                setQuantity(inputValue);
              }
            }}
          />
        </div>

        <div className="form-group">
          <label htmlFor="summary">Summary</label>
          <textarea
            id="summary"
            value={summary}
            onChange={(e) => setSummary(e.target.value)}
          ></textarea>
        </div>

        <button type="submit">Add Product</button>
      </form>
    </div>
  );
};

export default AddProductForm;
