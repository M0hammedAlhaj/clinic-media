import React from "react";

const SpecialitiesItem = ({ title, image }) => {
  return (
    <div>
      <a href="" className="flex flex-col items-center gap-2 text-center">
        <img src={image} alt="" className="min-w-[5rem]"/>
        <h3 className="text-[1rem] font-bold">{title}</h3>
      </a>
    </div>
  );
};

export default SpecialitiesItem;
