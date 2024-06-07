import React, { useState } from "react";
import Base from "../components/Base";
import { Button, Card, CardBody, CardHeader, Container, FormGroup, Input, Label, Row ,Col,Form} from "reactstrap";
import { toast } from "react-toastify";
import { loginUser } from "../Service/user-service";
import { doLogin } from "../Auth/Index";
import { useNavigate } from "react-router-dom";

const Login=()=>{

    const [loginDetail,setLoginDetail]=useState({email:'',password:''});
    const navigate=useNavigate()
    //create HandleChange function
    const HandleChange=(event,field)=>{
        let actualValue=event.target.value
        setLoginDetail({...loginDetail,[field]:actualValue})
        
    }

    // create Handle Submit function
    const HandleSubmit=(event)=>{
        event.preventDefault();
        console.log(loginDetail);

        // handle the validation
        if(loginDetail.email.trim()==''||loginDetail.password.trim()==''){
            toast.error("username and Password required !!");
        }
        // submit the data to server to generate jwt token
            loginUser(loginDetail).then((jwtTokenData) =>{
                console.log("user login :");
                
        // save the data to local storage
            doLogin (jwtTokenData,()=>{
                console.log("data saved in local storage !!");
        // Redirect to user dashboard page
                navigate("/user/dashboard")
            });

                toast.success("login success !!")
            }).catch((error)=>{
                console.log(error);
                toast.error("something went wrong !!")
            })
            
    };

    //create handleReset function
    const HandleReset=()=>{
     setLoginDetail({email:'',password:''});   

    };
    return(
<>
<Base>
<Container>
    <Row className="mt-4">
        <Col sm={{size:4,offset:8}}>
        <Card color="light" >
            <CardHeader>
                <h4>Fill the Requirment to login.</h4>
            </CardHeader>
            <CardBody>
                <Form  onSubmit={HandleSubmit}>

                    {/* Email field */}
                    <FormGroup>
                        <Label>Enter Email</Label>
                        <Input type="text" placeholder="Enter Here" id="email"
                        value={loginDetail.email}
                        onChange={(event)=>{HandleChange(event,"email")}}

                        />

                    </FormGroup>

                     {/* Password field */}
                     <FormGroup>
                        <Label>Enter Password</Label>
                        <Input type="password" placeholder="Enter Here" id="password"
                        value={loginDetail.password}
                        onChange={(event)=>{HandleChange(event,"password")}}
                        />
                    </FormGroup>
                    <Container>
                        <Button color="primary">Login</Button>
                        <Button onClick={HandleReset} color="light" className="ms-2" type="reset" >Reset</Button>
                    </Container>
                    <a href="/signup">Create an Account</a>
                </Form>
            </CardBody>
        </Card>
        </Col>
    </Row>
</Container>
</Base>
</>
    );
}
export default Login;