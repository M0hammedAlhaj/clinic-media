import { useState } from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./components/HomePage/HomePage";
import Login from "./components/Login";
function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/auth" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
