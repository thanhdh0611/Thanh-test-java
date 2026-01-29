import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import "./App.css";
import Home from "./pages/user/Home";
import Detail from './pages/user/Detail';
import Login from './pages/user/Login';
import Registration from './pages/user/Registration';
import Cart from './pages/user/Cart'
import Admin from './pages/admin/Admin';
import Shop from './pages/user/Shop';
import AboutPerson from './pages/user/AboutPerson';

import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/product/:id" element={<Detail />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Registration />} />
        <Route path='/cart' element={<Cart />} />
        <Route path='/addproduct' element={<Admin />} />
        <Route path='/shop' element={<Shop />} />
        <Route path='/about' element={<AboutPerson />} />
      </Routes>
    </BrowserRouter>
  )
};

export default App;