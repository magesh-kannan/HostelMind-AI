import api from "../api/api";

export const getAllHostels = async () => {

    const token = localStorage.getItem("token");

    const response = await api.get("/hostels", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

    return response.data;
};

export const createHostel = async (hostel) => {

    const token = localStorage.getItem("token");

    const response = await api.post(
        "/hostels",
        hostel,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const updateHostel = async (id, hostel) => {

    const token = localStorage.getItem("token");

    const response = await api.put(
        `/hostels/${id}`,
        hostel,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteHostel = async (id) => {

    const token = localStorage.getItem("token");

    const response = await api.delete(
        `/hostels/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};