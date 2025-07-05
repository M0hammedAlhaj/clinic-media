import React from "react";
import Search from "../Search";
import Navbar from "../Navbar";
import Specialities from "./Specialities";
import Doctor from "./Doctor";
import Features from "./Features";
import Footer from "../Footer";

const HomePage = () => {
  return (
    <>
      <div className="flex flex-col  flex-wrap w-full">
        <Navbar />
        <Search />
        <Specialities />
        <Doctor />
        <Features />
        <Footer />
      </div>
    </>
  );
};

export default HomePage;
