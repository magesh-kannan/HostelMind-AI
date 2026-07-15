import api from "../api/api";

export const getAllStudents = async () => {

    const token = localStorage.getItem("token");

    const response = await api.get("/students", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

    return response.data;
};

export const createStudent = async (student) => {

    const token = localStorage.getItem("token");

    const response = await api.post(
        "/students",
        student,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const updateStudent = async (id, student) => {

    const token = localStorage.getItem("token");

    const response = await api.put(
        `/students/${id}`,
        student,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteStudent = async (id) => {

    const token = localStorage.getItem("token");

    const response = await api.delete(
        `/students/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};