import React from "react";

const SpecialitiesItem = ({ title, image }) => {
  return (
    <div className="bg-gray-50 p-12 rounded-[25%] hover:shadow-xl hover:scale-105 transition-transform duration-300 cursor-pointer">
      <a href="#" className="flex flex-col items-center gap-2 text-center">
        <img src={image} alt={title} className="max-w-[3rem] md:max-w-[5rem]" />
        <h3>{title}</h3>
      </a>
    </div>
  );
};

export default SpecialitiesItem;
