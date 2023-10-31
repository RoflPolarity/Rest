import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import Register from './register';
const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path='/register' element ={<Register/>}/>
        {/* Другие маршруты */}
      </Routes>
    </Router>
  );
};

export default App;
