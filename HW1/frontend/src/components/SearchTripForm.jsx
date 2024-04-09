import React, { useState, useEffect } from 'react';
import axios from 'axios'; // Import axios for making HTTP requests

function SearchTripForm(props) {
  // State to store form data
  const [formData, setFormData] = useState({
    origin: '',
    destination: '',
    date: ''
  });
  // State to store currency options
  const [currencyOptions, setCurrencyOptions] = useState([]);
  const [selectedCurrency, setSelectedCurrency] = useState('EUR');
  const [currencyRate, setCurrencyRate] = useState(1.0);

  const fetchCurrencyOptions = async () => {
    // Check if the data is in local storage
    const storedData = localStorage.getItem('currencyOptions');

    if (storedData && storedData.length > 1) {
      // If the data is in local storage, parse it and set it in state
      setCurrencyOptions(JSON.parse(storedData));
      console.log("Currency options from local storage")
    } else {
      // If the data is not in local storage, fetch it from the API
      try {
        const response = await axios.get('http://localhost:8080/api/currencies');
        setCurrencyOptions(response.data);

        // Store the data in local storage for future use
        localStorage.setItem('currencyOptions', JSON.stringify(response.data));
        console.log("Currency options from API");

      } catch (error) {
        console.error('Error fetching currency options:', error);
      }
    }
  };

  // Fetch currency options when component mounts
  useEffect(() => {
    if (currencyOptions.length <= 1) {
      fetchCurrencyOptions();
    }
  }, [currencyOptions]);

  const handleCurrencyChange = (e) => {
    setSelectedCurrency(e.target.value);
  };

  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission behavior

    try {
      // Make HTTP request to endpoint with form data
      const response = await axios.get('http://localhost:8080/api/trips', {
        params: {
          startLocation: formData.origin || null,
          endLocation: formData.destination || null,
          dateTime: formData.date || null
        }
      });

      // Handle successful response
      console.log('Trips:', response.data);
      props.onTrips(response.data);
      

    } catch (error) {
      // Handle error
      console.error('Error fetching trips:', error);
      alert('No trips found');
    }
    if (selectedCurrency === 'EUR') {
      setCurrencyRate(1.0);
      props.onRate(1.0);
      return;
    }
    try {
      var response = ""
      if (!selectedCurrency) {
        response = await axios.get('http://localhost:8080/api/currency/EUR');

      }else{
        response = await axios.get('http://localhost:8080/api/currency/' + selectedCurrency);
      }
      if (selectedCurrency) {
        console.log('Currency', selectedCurrency);
        props.onCurrency(selectedCurrency);
      } else {
        props.onCurrency("EUR");
      }
      console.log("rate ", response.data)
      setCurrencyRate(response.data.eurRate);
      props.onRate(response.data.eurRate);
    } catch (error) {
      alert('Either the currency is not supported or the API is in couldown. Please try again later.');
    }
  };

  // Function to handle input changes and update form data state
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  return (
    <form className="flex flex-col space-y-4 bg-white shadow-lg rounded-lg p-8" onSubmit={handleSubmit}>
      <div>
        <label htmlFor="origin" className="block text-sm font-medium text-gray-700">Origin</label>
        <input type="text" id="origin" name="origin" className="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" value={formData.origin} onChange={handleChange} />
      </div>

      <div>
        <label htmlFor="destination" className="block text-sm font-medium text-gray-700">Destination</label>
        <input type="text" id="destination" name="destination" className="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" value={formData.destination} onChange={handleChange} />
      </div>

      <div>
        <label htmlFor="date" className="block text-sm font-medium text-gray-700">Date</label>
        <input type="date" id="date" name="date" className="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" value={formData.date} onChange={handleChange} />
      </div>

      <div>
        <label htmlFor="currency" className="block text-sm font-medium text-gray-700">Currency</label>
        <select id="currency" name="currency" className="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" value={selectedCurrency} onChange={handleCurrencyChange}>
          <option value="">EUR</option>
          {currencyOptions.map((currency) => (
            <option key={currency} value={currency}>{currency}</option>
          ))}
        </select>
      </div>

      <button type="submit" className="py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-600">Submit</button>
    </form>
  );
}

export default SearchTripForm;
