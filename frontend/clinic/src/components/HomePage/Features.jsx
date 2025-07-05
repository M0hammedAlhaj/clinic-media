import React from "react";
import FeaturesItem from "./FeaturesItem";
const featuresData = [
  {
    title: "حجز مواعيد سهلة",
    description: "يمكنك حجز موعد مع الطبيب بسهولة عبر الإنترنت في أي وقت",
  },
  {
    title: "تواصل مباشر مع الأطباء",
    description:
      "الدردشة الفورية مع الأطباء للاستفسار والمتابعة دون الحاجة لزيارة العيادة",
  },
  {
    title: "السجلات الطبية الرقمية",
    description: "كل سجلاتك الطبية محفوظة وآمنة ويمكنك الوصول إليها في أي وقت",
  },
  {
    title: "خدمات مختبر متكاملة",
    description:
      "إجراء التحاليل المخبرية والحصول على النتائج مباشرة عبر النظام",
  },
];

const Features = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 px-40 gap-6 my-25">
      {featuresData.map((feature, index) => (
        <FeaturesItem
          key={index}
          title={feature.title}
          description={feature.description}
        />
      ))}
    </div>
  );
};

export default Features;
