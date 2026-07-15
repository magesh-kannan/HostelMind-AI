import { BrowserRouter, Routes, Route } from "react-router-dom";

import LoginPage from "../pages/Login/LoginPage";
import DashboardPage from "../pages/Dashboard/DashboardPage";
import HostelPage from "../pages/Hostel/HostelPage";
import RoomPage from "../pages/Room/RoomPage";
import StudentPage from "../pages/Student/StudentPage";
import MainLayout from "./MainLayout";

function AppRoutes() {

    return (

        <BrowserRouter>

            <Routes>

                {/* Public Route */}

                <Route
                    path="/"
                    element={<LoginPage />}
                />

                {/* Protected Routes */}

                <Route element={<MainLayout />}>

                    <Route
                        path="/dashboard"
                        element={<DashboardPage />}
                    />

                    <Route
                        path="/hostels"
                        element={<HostelPage />}
                    />

                    <Route
                        path="/rooms"
                        element={<RoomPage />}
                    />

                    <Route
                        path="/students"
                        element={<StudentPage />}
                    />

                </Route>

            </Routes>

        </BrowserRouter>

    );

}

export default AppRoutes;