import React, { useState, useEffect } from 'react';
import SearchTripForm from '../components/SearchTripForm';
import TripsTable from '../components/TripsTable';

function SearchTrip() {
  const [trips, setTrips] = useState([]);
  const [currency, setCurrency] = useState('EUR');
  const [rate, setRate] = useState(1.0);

  const handleTrips = (newTrips) => {
    setTrips(newTrips);
  };

  const handleCurrency = (newCurrency) => {
    setCurrency(newCurrency);
  }

  const handleRate = (newRate) => {
    setRate(newRate);
  }
  
  const handleBuy = (id) => {
    console.log('Bought trip with ID:', id);
  };
  useEffect(() => {
    document.title = 'Search Trip';
  }, []);
  return (
    <div className="container mx-auto px-4">
      <h1 className="text-2xl font-semibold mb-4">Search for a Trip</h1>
      <SearchTripForm onTrips={handleTrips} onCurrency={handleCurrency} onRate={handleRate} />
      <TripsTable trips={trips} cur={currency} rate={rate} onBuy={handleBuy} />
    </div>
  );
}

export default SearchTrip;
