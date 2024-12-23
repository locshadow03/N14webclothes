import axios from "axios";

export const api = axios.create({
    baseURL : "http://localhost:8080/contact"
})

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export async function addContact(fullName, email, phoneNumber, message) {
    const formData = new FormData()
    formData.append("fullName", fullName)
    formData.append("email", email)
    formData.append("phoneNumber", phoneNumber)
    formData.append("message", message)
    const response = await api.post("/add_contact", formData)
    return response.data
}

export async function getAllContact() {
    try{
        const result = await api.get("/all_contact")
        return result.data
    }catch(error){
        throw new Error("Error fetching contact")
    }
}

export async function deleteContact(contactId){
    try{
        const result = await api.delete(`/delete/${contactId}`)
        return result.data
    }catch(error){
        throw new Error(`Error deleting contact ${error.message}`)
    }
}