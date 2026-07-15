import { Outlet, Link, useLocation } from "react-router-dom";
import {
    AppBar,
    Toolbar,
    Typography,
    Box,
    Drawer,
    List,
    ListItem,
    ListItemButton,
    ListItemText,
} from "@mui/material";

const drawerWidth = 220;

function MainLayout() {

    const location = useLocation();

    return (

        <Box sx={{ display: "flex" }}>

            <AppBar
                position="fixed"
                sx={{ zIndex: 1201 }}
            >

                <Toolbar>

                    <Typography
                        variant="h6"
                        noWrap
                    >
                        HostelMind AI
                    </Typography>

                </Toolbar>

            </AppBar>

            <Drawer
                variant="permanent"
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,

                    "& .MuiDrawer-paper": {
                        width: drawerWidth,
                        boxSizing: "border-box",
                        mt: 8,
                    },
                }}
            >

                <List>

                    <ListItem disablePadding>

                        <ListItemButton
                            component={Link}
                            to="/dashboard"
                            selected={location.pathname === "/dashboard"}
                        >
                            <ListItemText primary="Dashboard" />
                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton
                            component={Link}
                            to="/hostels"
                            selected={location.pathname === "/hostels"}
                        >
                            <ListItemText primary="Hostels" />
                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton
                            component={Link}
                            to="/rooms"
                            selected={location.pathname === "/rooms"}
                        >
                            <ListItemText primary="Rooms" />
                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton
                            component={Link}
                            to="/students"
                            selected={location.pathname === "/students"}
                        >
                            <ListItemText primary="Students" />
                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton>

                            <ListItemText primary="Room Allocation" />

                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton>

                            <ListItemText primary="AI Features" />

                        </ListItemButton>

                    </ListItem>

                    <ListItem disablePadding>

                        <ListItemButton>

                            <ListItemText primary="Logout" />

                        </ListItemButton>

                    </ListItem>

                </List>

            </Drawer>

            <Box
                component="main"
                sx={{
                    flexGrow: 1,
                    p: 3,
                    mt: 8,
                    ml: `${drawerWidth}px`,
                }}
            >

                <Outlet />

            </Box>

        </Box>

    );

}

export default MainLayout;