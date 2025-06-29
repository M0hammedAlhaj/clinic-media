import { useState } from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Search from "./components/Search";
import Specialities from "./components/Specialities";
function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="flex flex-col  flex-wrap w-full">
        <Navbar />
        <Search />
        <Specialities />
      </div>
    </>
  );
}

export default App;
