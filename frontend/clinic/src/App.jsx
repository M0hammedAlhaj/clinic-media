import { useState } from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Search from "./components/Search";
import Specialities from "./components/Specialities";
import Doctor from "./components/Doctor";
import Features from "./components/Features";
import Footer from "./components/Footer";
function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="flex flex-col  flex-wrap w-full">
        <Navbar />
        <Search />
        <Specialities />
        <Doctor />
        <Features />
        <Footer/>
      </div>
    </>
  );
}

export default App;
