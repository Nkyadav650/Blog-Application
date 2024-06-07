// Import Bootstrap CSS in your main JavaScript file
import 'bootstrap/dist/css/bootstrap.min.css';
import React from "react";
import { Col, Container, Row } from "reactstrap";

const Footer = () => {
  return (
    <>
    
        <Row style={{ border: '0', color: 'white',backgroundColor:'GrayText' }}>
          <Col>
            <h1>This is footer</h1>
            <p>this is my company details</p>
            <h1>This is footer</h1>
            <p>this is my company details</p>
            <h1>This is footer</h1>
            <p>this is my company details</p>
            <h1>This is footer</h1>
            <p>this is my company details</p>
          </Col>
        </Row>
     
    </>
  );
};

export default Footer;
