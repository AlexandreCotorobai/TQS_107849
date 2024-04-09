import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const BuyTrip = () => {
  const { id } = useParams();
  const navigate = useNavigate(); // Define navigate here
  const [formData, setFormData] = useState({
    tripId: id,
    name: '',
    email: '',
    phone: '',
    creditCardNumber: '',
    cvv: '',
    expirationDate: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/tickets', formData);
      console.log(response.data);
      navigate('/details', { state: { id: response.data.id } });
    } catch (error) {
      console.error('Error buying ticket:', error);
    }
  };
  useEffect(() => {
    document.title = 'Buy Trip';
  }, []);

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-4">Buy Trip Page</h1>
      <p className="text-gray-600 mb-4">Trip ID: {id}</p>
      <div className="bg-white p-6 rounded-lg shadow-md">
        {/* Your BuyTrip page content */}
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="fullName">
              Full Name
            </label>
            <input
              type="text"
              id="fullName"
              name="name"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="Enter your full name"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="email">
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="Enter your email"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="phone">
              Phone
            </label>
            <input
              type="tel"
              id="phone"
              name="phone"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="Enter your phone number"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="creditCardNumber">
              Credit Card Number
            </label>
            <input
              type="text"
              id="creditCardNumber"
              name="creditCardNumber"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="Enter your credit card number"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="expirationDate">
              Expiry Date
            </label>
            <input
              type="text"
              id="expirationDate"
              name="expirationDate"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="MM/YY"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700" htmlFor="cvv">
              CVV
            </label>
            <input
              type="text"
              id="cvv"
              name="cvv"
              onChange={handleChange}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
              placeholder="Enter CVV"
              required
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring focus:ring-blue-500 focus:ring-opacity-50"
          >
            Confirm Purchase
          </button>
        </form>
      </div>
    </div>
  );
}

export default BuyTrip;
