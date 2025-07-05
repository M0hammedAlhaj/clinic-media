import React from "react";

const DoctorItem = ({ doctorImg, doctorName }) => {
  return (
    <>
      <div
        dir="rtl"
        className="bg-gray-100 rounded-l-2xl text-center shadow-lg hover:shadow-xl transition-shadow duration-300"
      >
        <a href="#" className="flex flex-col items-center gap-4">
          <img
            src={doctorImg}
            alt=""
            className="object-cover rounded-[.4rem]"
          />
          <h3 className="font-extrabold ">{doctorName}</h3>
          <div className="flex flex-col md:flex-row p-2 gap-2 text-[.8rem] md:text-[1rem]">
            <button className="btn_secondary p-2">عرض الملف</button>
            <button className="btn_primary p-2">احجز الآن</button>
          </div>
        </a>
      </div>
    </>
  );
};

export default DoctorItem;
