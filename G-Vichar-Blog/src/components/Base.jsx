import React from "react";
import CustomNavbar from "./CustomNavbar";
import Footer from "./Footer";
const Base = ({ Title = "Welcome to G-Vichar Blog", children }) => {
    return (
        <>
            <div className="container-fluid p-0 m-0">

                <div>
                    <CustomNavbar /><br /><br />
                </div>

                <div>
                    {children}
                </div>

                <div className="mt-1 Border-1 BorderColor-red">
                    <Footer />
                </div>

            </div>
        </>
    );
}
export default Base;