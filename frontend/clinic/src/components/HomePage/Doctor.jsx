import React from "react";
import DoctorItem from "./DoctorItem";
import doctorImageOne from "../../assets/doctors/doctor-thumb-01.jpg";
import doctorImageTwo from "../../assets/doctors/doctor-thumb-02.jpg";
import doctorImageThree from "../../assets/doctors/doctor-thumb-03.jpg";
import doctorImageFour from "../../assets/doctors/doctor-thumb-04.jpg";
import doctorImageFive from "../../assets/doctors/doctor-thumb-05.jpg";
import doctorImageSix from "../../assets/doctors/doctor-thumb-06.jpg";
const Doctor = () => {
  // Array of doctor images and names
  const doctors = [
    { img: doctorImageOne, name: "Jack" },
    { img: doctorImageTwo, name: "John" },
    { img: doctorImageThree, name: "Doe" },
    { img: doctorImageFour, name: "Smith" },
    { img: doctorImageFive, name: "Jane" },
    { img: doctorImageSix, name: "Emily" },
  ];

  // Map through the doctors array to create DoctorItem components
  const doctorItems = doctors.map((doctor, index) => (
    <DoctorItem key={index} doctorImg={doctor.img} doctorName={doctor.name} />
  ));

  return (
    <>
      <div
        dir="rtl"
        className="flex flex-col  items-center text-center bg-gray-100 mx-5 md:mx-20 lg:mx-40 rounded-3xl py-10   "
      >
        <h1 className="text-4xl md:text-4xl lg:text-5xl font-bold text-center mb-3">
          اختر الطبيب المناسب
        </h1>
        <p className="text-gray-500 px-3 text-[.9rem] md:text-[1rem] lg:text-[1.1rem]">
          تصفح الأطباء واحجز موعدك بكل سهولة.
        </p>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-6  gap-8 px-10 py-10 mx-auto">
          {doctorItems}
        </div>
      </div>
    </>
  );
};

export default Doctor;
