import React from "react";
import Base from "./Base";
import NewFeed from "./NewFeed";
import { Col, Container, Row } from "reactstrap";
import CategorySideMenu from "./CategorySideMenu";
const Home=()=>{
    return(
<>
<Base>

   <Row className="mt-3">
    <Col md={2} className="pt-5 mt-3">
        <CategorySideMenu/>
    </Col>
    <Col md={10}>
    <NewFeed/>
    </Col>
   </Row>

</Base>
</>
    );
}
export default Home;