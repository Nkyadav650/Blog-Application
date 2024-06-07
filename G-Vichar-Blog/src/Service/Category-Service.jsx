import { myAxios} from "./Helper"; 
export const loadAllCategories = () => {
    return myAxios.get(`/categories/getAllCategory`)
      .then((response) => {
        return response.data; // Fix: use response.data instead of response.date
      })
      .catch((error) => {
        console.log(error);
      });
  };
  