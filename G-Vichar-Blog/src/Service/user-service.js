import { myAxios } from "./Helper";

export const signup = async (user) => {
  try {
    const response = await myAxios.post("/auth/register", user);

    console.log('Response:', response.data);
    return response.data;
  } catch (error) {
    console.error('Signup failed:', error);
    // Handle the error as needed
    throw error;
  }
};

export const loginUser= async (loginDetails) =>{
  try{
return myAxios.post("/auth/login",loginDetails).then((response)=>response.data)
 } catch (error) {
    console.error('Signup failed:', error);
    // Handle the error as needed
    throw error;
  }
}