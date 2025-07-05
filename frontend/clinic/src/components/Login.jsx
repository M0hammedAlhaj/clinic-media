import React, { useState } from "react";
import Navbar from "./Navbar";

const Login = () => {
  const [isLogin, setIsLogin] = useState(true);

  const toggleForm = () => {
    setIsLogin(!isLogin);
  };

  return (
    <>
      <Navbar />
      <div className="bg-gray-100 h-screen flex items-center justify-center overflow-hidden">
        <div className="w-full h-full max-w-6xl bg-white lg:rounded-2xl lg:shadow-2xl lg:max-h-[95vh] overflow-hidden">
          <div className="flex flex-col lg:flex-row h-full">
            {/* Left side - Image (hidden on mobile) */}
            <div className="hidden lg:flex lg:w-1/2 bg-gradient-to-br from-blue-600 via-blue-700 to-indigo-800 relative overflow-hidden">
              <div className="absolute inset-0 bg-black opacity-20"></div>
              <div className="relative z-10 flex flex-col justify-center items-center text-white p-12">
                <div className="text-center mb-8">
                  <div className="w-24 h-24 mx-auto mb-6 bg-white/20 rounded-full flex items-center justify-center backdrop-blur-sm">
                    <svg
                      className="w-12 h-12 text-white"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth={2}
                        d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"
                      />
                    </svg>
                  </div>
                  <h1 className="text-4xl font-bold mb-4">مرحباً بك</h1>
                  <p className="text-xl opacity-90 leading-relaxed">
                    {isLogin 
                      ? "سجل دخولك للوصول إلى خدماتنا الطبية المتميزة"
                      : "انضم إلينا واحصل على أفضل الخدمات الطبية"
                    }
                  </p>
                </div>
                
                {/* Decorative elements */}
                <div className="absolute top-10 right-10 w-20 h-20 bg-white/10 rounded-full"></div>
                <div className="absolute bottom-10 left-10 w-16 h-16 bg-white/10 rounded-full"></div>
                <div className="absolute top-1/2 left-20 w-12 h-12 bg-white/10 rounded-full"></div>
              </div>
            </div>

            {/* Right side - Form */}
            <div className="w-full lg:w-1/2 p-4 sm:p-6 lg:p-8 flex flex-col justify-center overflow-y-auto">
              <div className="w-full max-w-md mx-auto py-4">
                {/* Mobile header with icon */}
                <div className="flex justify-center mb-6 lg:hidden">
                  <span className="inline-block bg-gradient-to-r from-blue-500 to-indigo-600 rounded-full p-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="28"
                      height="28"
                      viewBox="0 0 24 24"
                      className="text-white"
                    >
                      <path
                        fill="currentColor"
                        d="M12 4a4 4 0 0 1 4 4a4 4 0 0 1-4 4a4 4 0 0 1-4-4a4 4 0 0 1 4-4m0 10c4.42 0 8 1.79 8 4v2H4v-2c0-2.21 3.58-4 8-4"
                      />
                    </svg>
                  </span>
                </div>
                
                <h2 className="text-xl sm:text-2xl font-bold text-center mb-3 text-gray-800">
                  {isLogin ? "تسجيل دخول المريض" : "إنشاء حساب جديد"}
                </h2>
                
                <p className="text-gray-600 text-center mb-6 text-sm">
                  {isLogin 
                    ? "أدخل بياناتك للوصول إلى حسابك." 
                    : "أنشئ حساباً جديداً للوصول إلى الخدمات."
                  }
                </p>
                
                <form className="space-y-4">
                  {!isLogin && (
                    <>
                      <div>
                        <label
                          htmlFor="fullName"
                          className="block text-gray-700 text-sm font-semibold mb-2"
                        >
                          الاسم الكامل *
                        </label>
                        <input
                          type="text"
                          id="fullName"
                          className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
                          required
                          placeholder="الاسم الكامل"
                        />
                      </div>
                      <div>
                        <label
                          htmlFor="phoneNumber"
                          className="block text-gray-700 text-sm font-semibold mb-2"
                        >
                          رقم الهاتف *
                        </label>
                        <input
                          type="tel"
                          id="phoneNumber"
                          className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
                          required
                          placeholder="07XXXXXXXX"
                          pattern="[0-9]{10}"
                        />
                        <p className="text-gray-500 text-xs mt-1">
                          يرجى إدخال رقم الهاتف بصيغة 10 أرقام
                        </p>
                      </div>
                    </>
                  )}
                  
                  <div>
                    <label
                      htmlFor="email"
                      className="block text-gray-700 text-sm font-semibold mb-2"
                    >
                      البريد الإلكتروني *
                    </label>
                    <input
                      type="email"
                      id="email"
                      className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
                      required
                      placeholder="example@you.com"
                    />
                  </div>
                  
                  <div>
                    <label
                      htmlFor="password"
                      className="block text-gray-700 text-sm font-semibold mb-2"
                    >
                      كلمة المرور *
                    </label>
                    <input
                      type="password"
                      id="password"
                      className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
                      required
                      placeholder="••••••••"
                    />
                    <p className="text-gray-500 text-xs mt-1">
                      يجب أن تحتوي على حرف كبير واحد، رقم واحد، وطول لا يقل عن 8 أحرف.
                    </p>
                  </div>
                  
                  {!isLogin && (
                    <div>
                      <label
                        htmlFor="confirmPassword"
                        className="block text-gray-700 text-sm font-semibold mb-2"
                      >
                        تأكيد كلمة المرور *
                      </label>
                      <input
                        type="password"
                        id="confirmPassword"
                        className="w-full px-4 py-2.5 border border-gray-300 rounded-lg text-gray-700 focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
                        required
                        placeholder="••••••••"
                      />
                    </div>
                  )}
                  
                  <div className="flex flex-col sm:flex-row gap-3 pt-2">
                    <button
                      type="submit"
                      className="flex-1 bg-gradient-to-r from-blue-500 to-blue-600 text-white px-6 py-2.5 rounded-xl font-semibold shadow-lg hover:from-blue-600 hover:to-blue-700 hover:shadow-xl transform hover:-translate-y-0.5 transition-all duration-200 focus:outline-none focus:ring-4 focus:ring-blue-300 focus:ring-opacity-50"
                    >
                      <span className="flex items-center justify-center gap-2">
                        {isLogin ? (
                          <>
                            <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
                            </svg>
                            تسجيل الدخول
                          </>
                        ) : (
                          <>
                            <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
                            </svg>
                            إنشاء الحساب
                          </>
                        )}
                      </span>
                    </button>
                    <button
                      type="button"
                      onClick={toggleForm}
                      className="flex-1 bg-gradient-to-r from-gray-100 to-gray-200 text-gray-700 px-6 py-2.5 rounded-xl font-semibold border-2 border-gray-300 hover:from-gray-200 hover:to-gray-300 hover:border-gray-400 hover:shadow-md transform hover:-translate-y-0.5 transition-all duration-200 focus:outline-none focus:ring-4 focus:ring-gray-300 focus:ring-opacity-50"
                    >
                      <span className="flex items-center justify-center gap-2">
                        {isLogin ? (
                          <>
                            <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
                            </svg>
                            إنشاء حساب
                          </>
                        ) : (
                          <>
                            <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
                            </svg>
                            تسجيل الدخول
                          </>
                        )}
                      </span>
                    </button>
                  </div>
                  
                  <div className="text-center pt-2">
                    <p className="text-gray-500 text-sm">
                      {isLogin ? "لا تملك حساباً؟" : "لديك حساب بالفعل؟"}
                    </p>
                    <p className="text-blue-600 text-xs mt-1 font-medium">
                      {isLogin ? "انقر على الزر الثاني لإنشاء حساب جديد" : "انقر على الزر الثاني لتسجيل الدخول"}
                    </p>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;