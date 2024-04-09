import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';

const Details = () => {
  const location = useLocation();
  const { id } = location.state;
  const [ticket, setTicket] = useState(null);
  const [trip, setTrip] = useState(null);

  useEffect(() => {
    const fetchTicketAndTrip = async () => {
      try {
        // Fetch ticket information
        const ticketResponse = await axios.get(`http://localhost:8080/api/tickets/${id}`);
        setTicket(ticketResponse.data);

        // Fetch trip information using tripId from the ticket
        const tripResponse = await axios.get(`http://localhost:8080/api/trips/${ticketResponse.data.tripId}`);
        setTrip(tripResponse.data);
      } catch (error) {
        console.error('Error fetching ticket and trip:', error);
      }
    };

    fetchTicketAndTrip();
  }, [id]);

  useEffect(() => {
    document.title = 'Details';
  }, []);
  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">Trip Details</h1>
      {ticket && trip && (
        <div className="bg-white shadow-md rounded-lg p-6">
          <div className="mb-8">
            <h2 className="text-lg font-semibold mb-4">Ticket Information</h2>
            <p><span className="font-semibold">Name:</span> {ticket.name}</p>
            <p><span className="font-semibold">Email:</span> {ticket.email}</p>
            <p><span className="font-semibold">Phone:</span> {ticket.phone}</p>
            <p><span className="font-semibold">Credit Card Number:</span> {ticket.creditCardNumber}</p>
            <p><span className="font-semibold">CVV:</span> {ticket.cvv}</p>
            <p><span className="font-semibold">Expiration Date:</span> {ticket.expirationDate}</p>
          </div>
          <div>
            <h2 className="text-lg font-semibold mb-4">Trip Information</h2>
            <p><span className="font-semibold">Origin:</span> {trip.origin}</p>
            <p><span className="font-semibold">Destination:</span> {trip.destination}</p>
            <p><span className="font-semibold">Departure Date/Time:</span> {trip.departureDateTime}</p>
            <p><span className="font-semibold">Arrival Date/Time:</span> {trip.arrivalDateTime}</p>
            <p><span className="font-semibold">Company:</span> {trip.company}</p>
            <p><span className="font-semibold">Available Seats:</span> {trip.availableSeats}</p>
          </div>
          <div className="text-center mt-4 mb-4">
            <p className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
              RESERVATION CODE: XPTO-{ticket.id}
            </p>
          </div>
        </div>
      )}

    </div>

  );
}

export default Details;
