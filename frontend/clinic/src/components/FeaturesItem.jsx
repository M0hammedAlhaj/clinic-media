import React from "react";

function FeaturesItem({ title, description }) {
  return (
    <div
      dir="rtl"
      className="
        flex 
        flex-col 
        sm:flex-row 
        items-center 
        gap-4 
        p-4 
        border 
        rounded-lg 
        shadow-sm 
        hover:shadow-md 
        transition-shadow 
        duration-300 
        bg-white 
        dark:bg-gray-800
      "
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        className="w-10 h-10 text-blue-500 flex-shrink-0"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        strokeWidth={2}
      >
        <circle cx="12" cy="12" r="10" />
      </svg>
      <div className="flex flex-col gap-1 text-right">
        <h2 className="text-lg font-semibold text-gray-900 dark:text-gray-100">
          {title}
        </h2>
        <p className="text-gray-600 dark:text-gray-300">{description}</p>
      </div>
    </div>
  );
}

export default FeaturesItem;
