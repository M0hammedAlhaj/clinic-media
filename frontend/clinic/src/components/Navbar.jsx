import React, { useState } from "react";

const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  return (
    <nav dir="rtl" className="bg-white border-b border-gray-200 dark:bg-gray-900">
      <div className="flex flex-wrap items-center justify-between max-w-screen-xl mx-auto px-6 py-6">
        {/* Logo */}
        <a href="/" className="flex items-center gap-3">
          <img
            src="https://flowbite.com/docs/images/logo.svg"
            className="h-10"
            alt="Logo"
          />
          <span className="self-center text-3xl font-bold whitespace-nowrap dark:text-white">
            كلينك
          </span>
        </a>

        {/* Right Side: Auth + Menu Button */}
        <div className="flex items-center md:order-2 gap-3">
          <a
            href="#"
            className="text-gray-800 dark:text-white text-lg hover:bg-gray-100 dark:hover:bg-gray-700
             font-medium rounded-lg px-3 py-2 md:px-5 lg:px-7"
          >
            تسجيل الدخول
          </a>
          <a
            href="#"
            className="text-white bg-blue-700 hover:bg-blue-800 dark:bg-blue-600
             dark:hover:bg-blue-700 text-lg font-medium rounded-lg px-3 py-2 md:px-5 lg:px-7"
          >
            إنشاء حساب
          </a>
          <button
            onClick={() => setIsMenuOpen(!isMenuOpen)}
            type="button"
            className="inline-flex items-center p-2 w-10 h-10 text-gray-500 rounded-lg md:hidden hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-gray-700"
          >
            <span className="sr-only">القائمة الرئيسية</span>
            <svg className="w-6 h-6" fill="none" viewBox="0 0 17 14" xmlns="http://www.w3.org/2000/svg">
              <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M1 1h15M1 7h15M1 13h15"/>
            </svg>
          </button>
        </div>

        {/* Menu Links */}
        <div
          id="mega-menu"
          className={`w-full md:flex md:w-auto md:order-1 ${isMenuOpen ? "flex" : "hidden"}`}
        >
          <ul className="flex flex-col mt-4 text-lg font-medium md:flex-row-reverse md:mt-0 md:gap-10">
            <li>
              <a
                href="#"
                className="block py-2 px-4 text-blue-700 dark:text-blue-500 hover:underline"
              >
                الصفحة الرئيسية
              </a>
            </li>
            <li className="relative">
              <button
                onClick={() => setIsDropdownOpen(!isDropdownOpen)}
                className="flex items-center gap-2 py-2 px-4 text-gray-900 dark:text-white hover:underline"
              >
                الشركة
                <svg
                  className={`w-4 h-4 transform transition-transform ${
                    isDropdownOpen ? "rotate-180" : ""
                  }`}
                  fill="none"
                  viewBox="0 0 10 6"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    stroke="currentColor"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="m1 1 4 4 4-4"
                  />
                </svg>
              </button>

              {/* Dropdown */}
              {isDropdownOpen && (
                <div className="absolute right-0 mt-2 w-56 bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-lg shadow-lg z-50">
                  <ul className="py-2 text-sm text-gray-700 dark:text-gray-200">
                    <li>
                      <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600">من نحن</a>
                    </li>
                    <li>
                      <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600">المدونة</a>
                    </li>
                    <li>
                      <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600">المصادر</a>
                    </li>
                    <li>
                      <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600">الدعم الفني</a>
                    </li>
                  </ul>
                </div>
              )}
            </li>
            <li>
              <a
                href="#"
                className="block py-2 px-4 text-gray-900 dark:text-white hover:underline"
              >
                الفريق
              </a>
            </li>
            <li>
              <a
                href="#"
                className="block py-2 px-4 text-gray-900 dark:text-white hover:underline"
              >
                اتصل بنا
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
