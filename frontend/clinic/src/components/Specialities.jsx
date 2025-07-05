import React from "react";
import SpecialitiesItem from "./SpecialitiesItem";
import SpecialitiesOne from "../assets/specialities/specialities-01.png";
import SpecialitiesTow from "../assets/specialities/specialities-02.png";
import SpecialitiesThree from "../assets/specialities/specialities-03.png";
import SpecialitiesFour from "../assets/specialities/specialities-04.png";
import SpecialitiesFive from "../assets/specialities/specialities-05.png";
const Specialities = () => {
  return (
    <div
      className="flex flex-col items-center gap-8 px-6 py-6 text-center my-20"
      dir="rtl"
    >
      <h1 className="text-4xl md:text-4xl lg:text-5xl font-bold text-center">
        العيادات والتخصصات
      </h1>
      <p className="text-gray-500 px-3 text-[.9rem] md:text-[1rem] lg:text-[1.1rem] ">
        نقدم لك مجموعة من التخصصات الطبية لتلبية جميع احتياجاتك الصحية بكل
        احترافية.
      </p>
      <ul className="grid grid-cols-3 md:grid-cols-4  gap-10 m-auto ">
        <SpecialitiesItem title={"باطني"} image={SpecialitiesOne} />
        <SpecialitiesItem title={"جراحة"} image={SpecialitiesTow} />
        <SpecialitiesItem title={"عظام"} image={SpecialitiesThree} />
        <SpecialitiesItem title={"قلب"} image={SpecialitiesFour} />
        <SpecialitiesItem title={"أسنان"} image={SpecialitiesFive} />
        <SpecialitiesItem title={"باطني"} image={SpecialitiesOne} />
        <SpecialitiesItem title={"جراحة"} image={SpecialitiesTow} />
        <SpecialitiesItem title={"عظام"} image={SpecialitiesThree} />
      </ul>
    </div>
  );
};

export default Specialities;
