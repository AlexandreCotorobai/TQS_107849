import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Stats() {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/stats');
        setStats(response.data);
        setLoading(false);
      } catch (error) {
        setError(error.message);
        setLoading(false);
      }
    };

    fetchStats();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">Statistics</h1>
      {stats && (
        <ul className="list-disc pl-5 space-y-2">
          <li className="text-lg">API Calls: <span className="font-bold">{stats.apiCalls}</span></li>
          <li className="text-lg">Cache Hits: <span className="font-bold">{stats.cacheHits}</span></li>
          <li className="text-lg">API Misses: <span className="font-bold">{stats.apiMisses}</span></li>
        </ul>
      )}
    </div>
  );
}

export default Stats;
