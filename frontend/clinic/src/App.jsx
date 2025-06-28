import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Navbar from "./components/Navbar";
import SearchForm from "./components/SearchForm";
function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="flex flex-col gap-10  md:gap-20 lg:gap-30 flex-wrap w-">
        <Navbar />

        <div
          className="flex flex-col justify-center 
        items-center text-center  gap-4  px-10  "
        >
          <h1 className="text-3xl font-bold md:text-4xl lg:text-5xl">
            Search Doctor, Make an Appointment
          </h1>
          <p className="text-gray-500 text-[.9rem] md:text-[1rem] lg:text-[1.2rem]">
            Discover the best doctors, clinic & hospital the city nearest to
            you.
          </p>
          <div className=" w-2xl">
            <SearchForm />
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
