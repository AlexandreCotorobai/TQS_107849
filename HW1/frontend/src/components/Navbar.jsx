import React, { useState } from 'react';
import { useNavigate , Link } from 'react-router-dom';
import axios, { all } from 'axios';

function Navbar() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();

    const parts = searchTerm.split('-');
    const id = parts[1];

    try {
      const ticketResponse = await axios.get(`http://localhost:8080/api/tickets/${id}`);
      console.log(ticketResponse.data); // Use ticketResponse here
      navigate('/details', { state: { id: ticketResponse.data.id } });
    } catch (error) {
      console.error('Error searching ticket:', error);
      alert('Ticket not found');
    }
  };

  return (
    <nav className="bg-gray-800">
      <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
        <div className="relative flex items-center justify-between h-16">
          {/* Navbar content */}
          <div className="flex items-center">
            <div className="flex-shrink-0">
              <a href="/" className="text-white text-2xl font-semibold">Bus Tickets</a>
            </div>
            <div className="ml-10">
              <Link to="/stats" className="text-white text-m">Stats</Link>
            </div>
          </div>
          {/* Search input */}
          <div className="flex items-center">
            <form onSubmit={handleSearch}>
              <input
                type="text"
                placeholder="Ticket code"
                className="py-1 px-2 rounded-md focus:outline-none focus:ring focus:border-blue-300"
                value={searchTerm}
                onChange={(event) => setSearchTerm(event.target.value)}
              />
              <button type="submit" className="ml-2 py-1 px-3 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                Search
              </button>
            </form>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
