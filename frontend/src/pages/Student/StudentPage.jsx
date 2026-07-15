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
    getAllStudents,
    createStudent,
    updateStudent,
    deleteStudent
} from "../../services/studentService";

import { getAllHostels } from "../../services/hostelService";
import { getAllRooms } from "../../services/roomService";

function StudentPage() {

    const [students, setStudents] = useState([]);
    const [hostels, setHostels] = useState([]);
    const [rooms, setRooms] = useState([]);

    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(false);

    const [student, setStudent] = useState({
        id: null,
        registerNumber: "",
        fullName: "",
        email: "",
        phone: "",
        department: "",
        yearOfStudy: "",
        hostelId: "",
        roomId: "",
        active: true
    });

    useEffect(() => {
        loadStudents();
        loadHostels();
        loadRooms();
    }, []);

    const loadStudents = async () => {

        try {

            const data = await getAllStudents();

            setStudents(data);

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

    const loadRooms = async () => {

        try {

            const data = await getAllRooms();

            setRooms(data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleOpen = () => {

        setEditing(false);

        setStudent({
            id: null,
            registerNumber: "",
            fullName: "",
            email: "",
            phone: "",
            department: "",
            yearOfStudy: "",
            hostelId: "",
            roomId: "",
            active: true
        });

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleChange = (e) => {

        setStudent({
            ...student,
            [e.target.name]: e.target.value
        });

    };

    const handleEdit = (selectedStudent) => {

        setEditing(true);

        setStudent({
            ...selectedStudent
        });

        setOpen(true);

    };

    const handleSave = async () => {

        try {

            const payload = {

                registerNumber: student.registerNumber,
                fullName: student.fullName,
                email: student.email,
                phone: student.phone,
                department: student.department,
                yearOfStudy: Number(student.yearOfStudy),
                hostelId: Number(student.hostelId),
                roomId: Number(student.roomId),
                active: student.active

            };

            if (editing) {

                await updateStudent(student.id, payload);

                alert("Student updated successfully.");

            } else {

                await createStudent(payload);

                alert("Student created successfully.");

            }

            handleClose();

            loadStudents();

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
            "Are you sure you want to delete this student?"
        );

        if (!confirmDelete) return;

        try {

            await deleteStudent(id);

            alert("Student deleted successfully.");

            loadStudents();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Failed to delete student."
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
                    Student Management
                </Typography>

                <Button
                    variant="contained"
                    onClick={handleOpen}
                >
                    Add Student
                </Button>

            </Box>

            <TableContainer component={Paper}>

                <Table>

                    <TableHead>

                        <TableRow>

                            <TableCell>ID</TableCell>
                            <TableCell>Register No</TableCell>
                            <TableCell>Student Name</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Phone</TableCell>
                            <TableCell>Department</TableCell>
                            <TableCell>Year</TableCell>
                            <TableCell>Hostel</TableCell>
                            <TableCell>Room</TableCell>
                            <TableCell align="center">Actions</TableCell>

                        </TableRow>

                    </TableHead>

                    <TableBody>

                        {students.map((student) => (

                            <TableRow key={student.id}>

                                <TableCell>{student.id}</TableCell>

                                <TableCell>{student.registerNumber}</TableCell>

                                <TableCell>{student.fullName}</TableCell>

                                <TableCell>{student.email}</TableCell>

                                <TableCell>{student.phone}</TableCell>

                                <TableCell>{student.department}</TableCell>

                                <TableCell>{student.yearOfStudy}</TableCell>

                                <TableCell>
                                    {
                                        hostels.find(h => h.id === student.hostelId)?.hostelName ||
                                        student.hostelId
                                    }
                                </TableCell>

                                <TableCell>
                                    {
                                        rooms.find(r => r.id === student.roomId)?.roomNumber ||
                                        student.roomId
                                    }
                                </TableCell>

                                <TableCell align="center">

                                    <IconButton
                                        color="primary"
                                        onClick={() => handleEdit(student)}
                                    >
                                        <EditIcon />
                                    </IconButton>

                                    <IconButton
                                        color="error"
                                        onClick={() => handleDelete(student.id)}
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

                    {editing ? "Edit Student" : "Add Student"}

                </DialogTitle>

                <DialogContent>

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Register Number"
                        name="registerNumber"
                        value={student.registerNumber}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Full Name"
                        name="fullName"
                        value={student.fullName}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Email"
                        name="email"
                        value={student.email}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Phone"
                        name="phone"
                        value={student.phone}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Department"
                        name="department"
                        value={student.department}
                        onChange={handleChange}
                    />

                    <TextField
                        fullWidth
                        margin="normal"
                        label="Year of Study"
                        name="yearOfStudy"
                        type="number"
                        value={student.yearOfStudy}
                        onChange={handleChange}
                    />

                    <TextField
                        select
                        fullWidth
                        margin="normal"
                        label="Hostel"
                        name="hostelId"
                        value={student.hostelId}
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

                    <TextField
                        select
                        fullWidth
                        margin="normal"
                        label="Room"
                        name="roomId"
                        value={student.roomId}
                        onChange={handleChange}
                    >

                        {rooms.map((room) => (

                            <MenuItem
                                key={room.id}
                                value={room.id}
                            >
                                {room.roomNumber}
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

export default StudentPage;