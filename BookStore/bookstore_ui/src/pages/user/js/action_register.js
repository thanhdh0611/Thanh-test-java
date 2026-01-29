import { URLs } from "../../../constants";

export const registerUser =  async (userData) => {
    try {
      const response = await fetch(`${URLs}login/signup`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });
  
      if (!response.ok) {
        throw new Error("Đăng ký không thành công");
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error("Đăng ký không thành công");
    }
  };