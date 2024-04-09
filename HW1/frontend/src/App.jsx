import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './layouts/Layout'; // Assuming you have a Layout component

// Import your page components
import SearchTrip from './pages/SearchTrip';
import BuyTrip from './pages/BuyTrip';
import Details from './pages/Details';
import Stats from './pages/Stats';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<SearchTrip />} />
          <Route path="buyTrip/:id" element={<BuyTrip />} />
          <Route path="details" element={<Details />} />
          <Route path="stats" element={<Stats />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;