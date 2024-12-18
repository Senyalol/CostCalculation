import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import StartPage from './start.jsx';
import AboutPage from './about.jsx';
import CalcPage from './calc.jsx';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
  <Routes>

  
  <Route path="/" element={<StartPage />}/>
  <Route path="/about" element={<AboutPage />}/>
  <Route path="/calculation" element={<CalcPage />}/>
  </Routes>
  </BrowserRouter>
);


reportWebVitals();
