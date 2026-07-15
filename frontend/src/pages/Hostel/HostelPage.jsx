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
    IconButton
} from "@mui/material";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

import {
    getAllHostels,
    createHostel,
    updateHostel,
    deleteHostel
} from "../../services/hostelService";

function HostelPage() {

    const [hostels, setHostels] = useState([]);
    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(false);

    const [hostel, setHostel] = useState({
        id: null,
        hostelName: "",
        address: "",
        totalRooms: "",
        totalBeds: "",
        active: true
    });

    useEffect(() => {
        loadHostels();
    }, []);

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

        setHostel({
            id: null,
            hostelName: "",
            address: "",
            totalRooms: "",
            totalBeds: "",
            active: true
        });

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleChange = (e) => {

        setHostel({
            ...hostel,
            [e.target.name]: e.target.value
        });

    };

    const handleEdit = (selectedHostel) => {

        setEditing(true);

        setHostel({
            ...selectedHostel
        });

        setOpen(true);

    };

    const handleSave = async () => {

        try {

            const payload = {

                hostelName: hostel.hostelName,
                address: hostel.address,
                totalRooms: Number(hostel.totalRooms),
                totalBeds: Number(hostel.totalBeds),
                active: hostel.active

            };

            if (editing) {

                await updateHostel(hostel.id, payload);

                alert("Hostel updated successfully.");

            } else {

                await createHostel(payload);

                alert("Hostel created successfully.");

            }

            handleClose();

            loadHostels();

        } catch (error) {

            console.error(error);

            alert("Operation failed.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this hostel?"
        );

        if (!confirmDelete) return;

        try {

            await deleteHostel(id);

            alert("Hostel deleted successfully.");

            loadHostels();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Failed to delete hostel."
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
                    Hostel Management
                </Typography>

                <Button
                    variant="contained"
                    onClick={handleOpen}
                >
                    Add Hostel
                </Button>

            </Box>

            <TableContainer component={Paper}>

                <Table>

                    <TableHead>

                        <TableRow>

                            <TableCell>ID</TableCell>
                            <TableCell>Hostel Name</TableCell>
                            <TableCell>Address</TableCell>
                            <TableCell>Total Rooms</TableCell>
                            <TableCell>Total Beds</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell align="center">Actions</TableCell>

                        </TableRow>

                    </TableHead>

                    <TableBody>

                        {hostels.map((hostel) => (

                            <TableRow key={hostel.id}>

                                <TableCell>{hostel.id}</TableCell>
                                <TableCell>{hostel.hostelName}</TableCell>
                                <TableCell>{hostel.address}</TableCell>
                                <TableCell>{hostel.totalRooms}</TableCell>
                                <TableCell>{hostel.totalBeds}</TableCell>
                                <TableCell>

                                    {hostel.active ? "Active" : "Inactive"}

                                </TableCell>

                                <TableCell align="center">

                                    <IconButton
                                        color="primary"
                                        onClick={() => handleEdit(hostel)}
                                    >
                                        <EditIcon />
                                    </IconButton>

                                    <IconButton
                                        color="error"
                                        onClick={() => handleDelete(hostel.id)}
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

                    {editing ? "Edit Hostel" : "Add Hostel"}

                </DialogTitle>

                <DialogContent>

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Hostel Name"
                        name="hostelName"
                        value={hostel.hostelName}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Address"
                        name="address"
                        value={hostel.address}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Total Rooms"
                        name="totalRooms"
                        type="number"
                        value={hostel.totalRooms}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Total Beds"
                        name="totalBeds"
                        type="number"
                        value={hostel.totalBeds}
                        onChange={handleChange}
                    />

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

export default HostelPage;