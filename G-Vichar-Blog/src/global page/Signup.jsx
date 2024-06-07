import React, { useEffect, useState } from "react";
import Base from "../components/Base";
import { signup } from "../Service/user-service";
import {toast} from 'react-toastify';
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormFeedback, FormGroup, Input, Label, Row } from "reactstrap";
const Signup=()=>{

    const [data,setData]=useState({
        userName:'',email:'',password:'', contact:'', about:''
    })

    const [errors,setErrors]=useState({errorObject:{},isError:false})

    useEffect(()=>{ console.log(data);  },[data])
    //handle Event Change
    const HandleChange=(event,property)=>{
        setData({...data,[property]:event.target.value})
    }

    //Reset the data
    const resetDate=()=>{
        setData({
            userName:'',email:'',password:'', contact:'', about:''          
        })
        setErrors({isError:false})
    }

    //submit the data
    const submitForm=(event)=>{
        event.preventDefault()

        // if(errors.isError){
        //     toast.error("Form Data invalid ,correct it then submit !!")
        //     return;
        // }
       
        //Data validate

        // call server api to sending data
        signup(data).then((resp) => {
    console.log('Signup successful:', resp.data);
     toast.success("user register successfully !! user ID is :" +resp.userId)
    setData({
        userName:'',email:'',password:'', contact:'', about:''
        
    })  
    setErrors({isError:false})
  })
  .catch((error) => {
    console.error('Signup failed:', error);

    setErrors({ errorObject: error.response?.data?.Details || {},
    isError:true})
  });

    }

    return(
<>
<Base>
<Container >
   
    <Row className="mt-4">
    {JSON.stringify(data)}
        <Col sm={{size:4, offset:8}}>
        <Card color="secondary" inverse>
        <CardHeader>
            <h3>Fill Information to Register.</h3>
        </CardHeader>
        <CardBody>

            {/* creating Form */}
            <Form onSubmit={(event)=>submitForm(event)}>

                {/* Name field */}
                <FormGroup>
                    <Label>Enter Name</Label>
                    <Input type="text" 
                    placeholder="Enter Here" 
                    id="userName"  
                    onChange={(event)=>HandleChange(event,'userName')} 
                    value={data.userName}
                    invalid={errors.errorObject?.userName || false}
                    />
                      <FormFeedback >
                        { errors.errorObject?.userName}
                      </FormFeedback>
                </FormGroup>

                  {/* Email field */}
                <FormGroup>
                    <Label>Enter Email</Label>
                    <Input type="text" 
                    placeholder="Enter Here" 
                    id="email" 
                    onChange={(event)=>HandleChange(event,'email')} 
                    value={data.email}
                    invalid={errors.errorObject?.email || false}
                    />
                    <FormFeedback>
                        { errors.errorObject?.email}
                    </FormFeedback>
                </FormGroup>

                  {/* Password field */}
                <FormGroup>
                    <Label>Enter Password</Label>
                    <Input type="password" 
                    placeholder="Enter Here" 
                    id="password"  
                    onChange={(event)=>HandleChange(event,'password')} 
                    value={data.password}
                    invalid={errors.errorObject?.password || false}
                    />
                    <FormFeedback>
                        { errors.errorObject?.password}
                    </FormFeedback>
                </FormGroup>

                 {/* Contact field */}
                 <FormGroup>
                    <Label>Enter Contact number</Label>
                    <Input type="text" 
                    placeholder="Enter Here" 
                    id="contact"  
                    onChange={(event)=>HandleChange(event,'contact')} 
                    value={data.contact}
                    invalid={errors.errorObject?.contact || false}
                    />
                    <FormFeedback>
                        { errors.errorObject?.contact}
                    </FormFeedback>
                </FormGroup>

                  {/* Text area field */}
                <FormGroup>
                    <Label>Enter About</Label>
                    <Input 
                    type="textarea" 
                    placeholder="Enter Here" 
                    id="about" style={{height:"100px"}} 
                    onChange={(event)=>HandleChange(event,'about')}
                    value={data.about}
                    invalid={errors.errorObject?.about || false}
                    />
                    <FormFeedback>
                        { errors.errorObject?.about}
                    </FormFeedback>
                </FormGroup>

                <Container className="text-center">
                    <Button color="primary">Register</Button>
                    <Button onClick={resetDate} color="light" type="reset" className="ms-2">Reset</Button>
                </Container>
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
export default Signup;