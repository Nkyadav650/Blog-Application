import React from "react";

import { Button } from "react-bootstrap"; // Correct import statement
import Base from "../components/Base";
import App from "../App";
import userContext from "../context/userContext";

const About = () => {
  return (
    <>
    <userContext.Consumer>
  {user => (
    <Base>
      <h1>Welcome to G-Vichar About Page</h1>
      <Button variant="outline-warning" size="sm" style={{ marginRight: '10px' }}>click here</Button>
      <Button variant="outline-danger" size="md" src="App.jsx">click here</Button>
      <Button variant="outline-secondary" size="lg" src="App.jsx">click here</Button>
      <h1>welcome user: {user.name}</h1>
    </Base>
  )}
</userContext.Consumer>
   </>
  );
}

export default About;
