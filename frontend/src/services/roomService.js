import api from "../api/api";

export const getAllRooms = async () => {

    const token = localStorage.getItem("token");

    const response = await api.get("/rooms", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

    return response.data;
};

export const createRoom = async (room) => {

    const token = localStorage.getItem("token");

    const response = await api.post(
        "/rooms",
        room,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const updateRoom = async (id, room) => {

    const token = localStorage.getItem("token");

    const response = await api.put(
        `/rooms/${id}`,
        room,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteRoom = async (id) => {

    const token = localStorage.getItem("token");

    const response = await api.delete(
        `/rooms/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};