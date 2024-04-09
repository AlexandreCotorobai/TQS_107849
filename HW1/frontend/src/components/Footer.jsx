import React from 'react';

function Footer() {
  return (
    // <footer className=" py-4 mt-8 absolute inset-x-0 bottom-0">
    <footer className="-z-1 footer p-10 text-base-content mt-auto">
    <p>© {new Date().getFullYear()} TQS HW1. Made by Alexandre Cotorobai.</p>
  </footer>
  );
}

export default Footer;