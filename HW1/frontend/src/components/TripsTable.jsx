import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function TripsTable({ trips, cur, rate}) {
    const navigate = useNavigate();

    const adjustPrice = (price) => {
        if (rate) {
            return (price * rate).toFixed(2);
        }
        return price;
    };

    const handleBuy = (id) => {
        navigate(`/buyTrip/${id}`);
    };
    return (
        <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
                <tr>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">ID</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Origin</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Destination</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">Company</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">Price</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">Currency</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Departure Date/Time</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Arrival Date/Time</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">Available Seats</th>
                    <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">Actions</th>
                </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
                {trips.map((trip) => (
                    <tr key={trip.id}>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.id}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.origin}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.destination}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.price}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{adjustPrice(trip.price)}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{cur}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.departureDateTime}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.arrivalDateTime}</td>
                        <td className="px-4 py-4 whitespace-nowrap">{trip.availableSeats}</td>
                        <td className="px-4 py-4 whitespace-nowrap">
                            <button onClick={() => handleBuy(trip.id)} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Buy</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default TripsTable;
