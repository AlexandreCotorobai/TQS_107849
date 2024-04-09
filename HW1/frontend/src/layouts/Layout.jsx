import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./layout.css"; // Assuming you have a CSS file for layout styles

const Layout = () => {
    return (
        <>
        <div className="layout-container">
            <Navbar />
            <div className="pb-5 content-container">
                <Outlet />
            </div>
            <Footer />
        </div>
        </>
    );
};

export { Layout as default };