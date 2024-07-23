import React from "react";
import { Col, Container, Row } from "reactstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./footer.css"
//import Image from '../../buildingImage/Image';
const Footer = () => {
  return (
    <>
      <div class="footer">
        <div class="footer-section">
          <h4>Services</h4>
          <ul>
            <li><a href="#services">Construction</a></li>
            <li><a href="#services">Renovation</a></li>
            <li><a href="#services">Design-Build</a></li>
            <li><a href="#services">Project Management</a></li>
          </ul>
        </div>

        <div class="footer-section">
          <h4>Contact Us</h4>
          <p>Ambedkar chowk, Wiadhan, Dist-Singrauli,M.P.</p>
          <p>Pin-486886</p>
          <p>Email: Nkyadav650@gmail.com</p>
          <p>Phone: +91-9826795285</p>
          <p>Phone: +91-7879634456</p>
        </div>

        <div class="footer-section">
          <h2>Website</h2>
          <div class="social-icons">
            <div>
              <a href="https://www.facebook.com/nandkumar.yadav.127" target="_blank">
                {/* <img src={Image.facebooklogo} alt="Facebook" width="50px" height="50px" /> */}
                Facebook</a>
            </div>
            <div>
              <a href="https://twitter.com/home" target="_blank">
                {/* <img src={Image.twitterXlogo} alt="Twitter" width="50px" height="50px" /> */}
                Twitter</a>
            </div>
            <div>
              <a href="https://www.instagram.com/nandkumar_yadav650/?hl=en" target="_blank">
                {/* <img src={Image.instagramlogo} alt="Instagrm" width="50px" height="50px" /> */}
                Instagrm</a>
            </div>

          </div>
        </div>
      </div>

      <div class="footer-section1">
        <p>G-Vichar is a leading Blogging Website dedicated to delivering high-quality projects. Our team of professionals is committed to excellence Provide best Blogging Plateform.</p>
        <p>&copy; 2024 G-Vichar. All rights reserved.</p>
      </div>



    </>
  );
};

export default Footer;
