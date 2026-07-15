import { useEffect, useState } from "react";
import {
    Container,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Button,
    Box,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    IconButton,
    MenuItem
} from "@mui/material";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

import {
    getAllRooms,
    createRoom,
    updateRoom,
    deleteRoom
} from "../../services/roomService";

import { getAllHostels } from "../../services/hostelService";

function RoomPage() {

    const [rooms, setRooms] = useState([]);
    const [hostels, setHostels] = useState([]);

    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(false);

    const [room, setRoom] = useState({
        id: null,
        roomNumber: "",
        capacity: "",
        occupiedBeds: "",
        hostelId: ""
    });

    useEffect(() => {
        loadRooms();
        loadHostels();
    }, []);

    const loadRooms = async () => {

        try {

            const data = await getAllRooms();
            setRooms(data);

        } catch (error) {

            console.error(error);

        }

    };

    const loadHostels = async () => {

        try {

            const data = await getAllHostels();
            setHostels(data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleOpen = () => {

        setEditing(false);

        setRoom({
            id: null,
            roomNumber: "",
            capacity: "",
            occupiedBeds: "",
            hostelId: ""
        });

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleChange = (e) => {

        setRoom({
            ...room,
            [e.target.name]: e.target.value
        });

    };

    const handleEdit = (selectedRoom) => {

        setEditing(true);

        setRoom({
            ...selectedRoom
        });

        setOpen(true);

    };

    const handleSave = async () => {

        try {

            const payload = {
                roomNumber: room.roomNumber,
                capacity: Number(room.capacity),
                occupiedBeds: Number(room.occupiedBeds),
                hostelId: Number(room.hostelId)
            };

            if (editing) {

                await updateRoom(room.id, payload);

                alert("Room updated successfully.");

            } else {

                await createRoom(payload);

                alert("Room created successfully.");

            }

            handleClose();
            loadRooms();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Operation failed."
            );

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this room?"
        );

        if (!confirmDelete) return;

        try {

            await deleteRoom(id);

            alert("Room deleted successfully.");

            loadRooms();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Failed to delete room."
            );

        }

    };

    return (

        <Container sx={{ mt: 2 }}>

            <Box
                display="flex"
                justifyContent="space-between"
                alignItems="center"
                mb={2}
            >

                <Typography
                    variant="h4"
                    fontWeight="bold"
                >
                    Room Management
                </Typography>

                <Button
                    variant="contained"
                    onClick={handleOpen}
                >
                    Add Room
                </Button>

            </Box>

            <TableContainer component={Paper}>

                <Table>

                    <TableHead>

                        <TableRow>

                            <TableCell>ID</TableCell>
                            <TableCell>Room Number</TableCell>
                            <TableCell>Capacity</TableCell>
                            <TableCell>Occupied Beds</TableCell>
                            <TableCell>Hostel</TableCell>
                            <TableCell align="center">Actions</TableCell>

                        </TableRow>

                    </TableHead>

                    <TableBody>

                        {rooms.map((room) => (

                            <TableRow key={room.id}>
                                                                <TableCell>{room.id}</TableCell>

                                <TableCell>{room.roomNumber}</TableCell>

                                <TableCell>{room.capacity}</TableCell>

                                <TableCell>{room.occupiedBeds}</TableCell>

                                <TableCell>
                                    {
                                        hostels.find(
                                            hostel => hostel.id === room.hostelId
                                        )?.hostelName || room.hostelId
                                    }
                                </TableCell>

                                <TableCell align="center">

                                    <IconButton
                                        color="primary"
                                        onClick={() => handleEdit(room)}
                                    >
                                        <EditIcon />
                                    </IconButton>

                                    <IconButton
                                        color="error"
                                        onClick={() => handleDelete(room.id)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>

                                </TableCell>

                            </TableRow>

                        ))}

                    </TableBody>

                </Table>

            </TableContainer>

            <Dialog
                open={open}
                onClose={handleClose}
                fullWidth
                maxWidth="sm"
            >

                <DialogTitle>

                    {editing ? "Edit Room" : "Add Room"}

                </DialogTitle>

                <DialogContent>

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Room Number"
                        name="roomNumber"
                        value={room.roomNumber}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Capacity"
                        name="capacity"
                        type="number"
                        value={room.capacity}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Occupied Beds"
                        name="occupiedBeds"
                        type="number"
                        value={room.occupiedBeds}
                        onChange={handleChange}
                    />

                    <TextField
                        select
                        fullWidth
                        margin="normal"
                        label="Hostel"
                        name="hostelId"
                        value={room.hostelId}
                        onChange={handleChange}
                    >

                        {hostels.map((hostel) => (

                            <MenuItem
                                key={hostel.id}
                                value={hostel.id}
                            >
                                {hostel.hostelName}
                            </MenuItem>

                        ))}

                    </TextField>

                </DialogContent>

                <DialogActions>

                    <Button onClick={handleClose}>
                        Cancel
                    </Button>

                    <Button
                        variant="contained"
                        onClick={handleSave}
                    >
                        {editing ? "Update" : "Save"}
                    </Button>

                </DialogActions>

            </Dialog>

        </Container>

    );

}

export default RoomPage;