import React from "react";
import SearchForm from "./SearchForm";
const Search = () => {
  return (
    <div className="flex flex-col items-center gap-5 px-6 py-10 md:py-20 lg:py-30 bg-gray-200">
      <div className="text-center max-w-[60rem] flex flex-col gap-5">
        <h1 className="text-3xl font-extrabold md:text-5xl lg:text-6xl">
          ابحث عن طبيب، واحجز <span className="text-blue-700">موعد</span>
        </h1>
        <p className="text-gray-500 px-3 text-[.9rem] md:text-[1.1rem] lg:text-[1.3rem]">
          اكتشف أفضل الأطباء والعيادات والمستشفيات الأقرب إليك في المدينة.
        </p>
      </div>
      <div className="w-1/2">
        <SearchForm />
      </div>
    </div>
  );
};

export default Search;
