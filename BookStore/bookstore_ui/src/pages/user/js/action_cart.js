import { URLs } from "../../../constants";

export const sendPaymentData = async (data) => {
    try {
        // Lấy token từ local storage
        const token = localStorage.getItem("token");
      // Gửi dữ liệu lên server thông qua API
      const response = await fetch(`${URLs}order/addOrder`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `${token}`,
        },
        body: JSON.stringify(data),
      });
  
      // Xử lý response từ server
      if (response.ok) {
        console.log("Payment successful!");
        // Xóa dữ liệu giỏ hàng sau khi thanh toán thành công
        localStorage.removeItem("cartItems");
      } else {
        console.error("Payment failed.");
      }
    } catch (error) {
      console.error("Error sending payment data:", error);
    }
  };
  